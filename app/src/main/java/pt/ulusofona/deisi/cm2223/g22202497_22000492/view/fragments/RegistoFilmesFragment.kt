package pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemas_app.R
import com.example.cinemas_app.databinding.FragmentRegistoFilmesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.MovieRepository
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.*
import pt.ulusofona.deisi.cm2223.g22202497_22000492.view.adapters.ImagesAdapter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class RegistoFilmesFragment : Fragment() {

  private lateinit var binding: FragmentRegistoFilmesBinding
  private var movieRegistry: MovieRegistry = MovieRegistry()
  private var cinemaList: List<Cinema> = listOf()
  private var cinema: Cinema = Cinema()
  private val REQUEST_PICK_IMAGE = 100
  private var selectedImages = mutableListOf<Uri>()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_registo_filmes, container, false)
    binding = FragmentRegistoFilmesBinding.bind(view)
    cinemaList = History.loadCinemas(requireContext())
    pickDateClickEvent()
    rateChangeEvent()
    saveClickEvent()
    photosClickEvent()
    setupRevisitCheckBox()
    return binding.root
  }

  private fun pickDateClickEvent() {
    binding.registryPickDate.setOnClickListener {
      val calendar = Calendar.getInstance()
      val year = calendar.get(Calendar.YEAR)
      val month = calendar.get(Calendar.MONTH)
      val day = calendar.get(Calendar.DAY_OF_MONTH)

      val datePicker = DatePickerDialog(requireContext(), R.style.RedCalendar,
        { _, year, month, day ->
        val selectedDate = "$day/${month + 1}/$year"

        binding.registryPickDate.text = selectedDate
      }, year, month, day)
      datePicker.show()
    }
  }

  private fun photosClickEvent() {
    binding.registryPhotosAdd.setOnClickListener {
      // Create an intent to pick an image from the device
      val intent = Intent(Intent.ACTION_GET_CONTENT)
      intent.type = "image/*"
      intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
      // Launch the intent and wait for a result
      startActivityForResult(intent, REQUEST_PICK_IMAGE)
    }
  }

  private fun rateChangeEvent() {
    binding.registryRate.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
      override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        binding.registryRateValue.text = progress.toString()
      }

      override fun onStartTrackingTouch(seekBar: SeekBar?) {}

      override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    })
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK) {
      val clipData = data?.clipData

      if (clipData != null) {
        for (i in 0 until clipData.itemCount) {
          val imageUri = clipData.getItemAt(i).uri
          if (imageUri != null) {
            selectedImages.add(imageUri)
          }
        }
      } else {
        val imageUri = data?.data
        if (imageUri != null) {
          selectedImages.add(imageUri)
        }
      }

      binding.registryPhotosList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
      binding.registryPhotosList.adapter = ImagesAdapter(selectedImages)
    }
  }

  private fun setupRevisitCheckBox() {
    binding.checkBoxRevisit.setOnCheckedChangeListener { _, isChecked ->
      movieRegistry.isChecked = isChecked
    }
  }


  private fun saveClickEvent() {
    binding.registrySaveButton.setOnClickListener {

      if (validateInputs()) {
        val movieName = binding.registryMovieName.text.toString()
        val movieYear = binding.registryMovieYear.text.toString()
        val rate = binding.registryRate.progress
        val seenDate = binding.registryPickDate.text.toString()
        val observations = binding.registryObservations.text.toString()

        movieRegistry = MovieRegistry(
          cinema = cinema,
          rate = rate,
          seen = seenDate,
          isChecked = binding.checkBoxRevisit.isChecked,
          observations = observations
        )

        movieRegistry.movie.name = movieName
        movieRegistry.movie.year = movieYear

        movieRegistry.images = selectedImages.map {
          RegistryImage(
            uri = it.toString(),
            movieRegistryId = movieRegistry.id
          )
        }

        MovieRepository.getInstance().saveRegistry(movieRegistry) { result ->
          val isSuccess = result.isSuccess
          val errorMessage = if (!isSuccess) {
            result.exceptionOrNull()?.message
          } else {
            null
          }

          CoroutineScope(Dispatchers.Main).launch {
            if (isSuccess) {
              val message = "Movie was found: $movieName"
              Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
              Thread.sleep(5000)
              displayConfirm()
            } else {
              Toast.makeText(requireContext(), errorMessage.toString(), Toast.LENGTH_SHORT).show()
            }
          }
        }
      } else {
        Toast.makeText(requireContext(), getString(R.string.registry_error), Toast.LENGTH_SHORT).show()
      }
    }
  }

  private fun displayConfirm() {
    val confirmDialog = AlertDialog.Builder(requireContext())
      .setTitle(getString(R.string.dialog_confirm))
      .setMessage(getString(R.string.registry_dialog_message, movieRegistry.movie.name, movieRegistry.rate.toString()))
      .setPositiveButton(getString(R.string.dialog_save)) { _, _ ->
        Toast.makeText(requireContext(), getString(R.string.registry_success),
          Toast.LENGTH_SHORT).show()

        clearForm()
      }
      .setNegativeButton(getString(R.string.dialog_cancel), null)
      .create()
    confirmDialog.show()
  }

  private fun validateInputs(): Boolean {
    return validateMovieName() &&
           validateMovieYear() &&
           validateCinema() &&
           validateRate() &&
           validateSeenDate()
  }

  private fun validateMovieName() : Boolean {
    val movieName = binding.registryMovieName.text.toString()

    if (movieName.isBlank()) {
      binding.registryMovieName.error = getString(R.string.error_field_required)
      return false
    }

    return true
  }

  private fun validateMovieYear(): Boolean {
    val movieYear = binding.registryMovieYear.text.toString()

    if (movieYear.isBlank()) {
      binding.registryMovieYear.error = getString(R.string.error_field_required)
      return false
    }

    return true
  }


  private fun validateCinema(): Boolean {
    val cinemaName = binding.registryCinema.text.toString()

    if (cinemaName.isBlank()) {
      binding.registryCinema.error = getString(R.string.error_field_required)
      return false
    }

    var historyCinema = History.getCinemaByName(cinemaList, cinemaName)

    if (historyCinema == null) {
      binding.registryMovieName.error = getString(R.string.cinema_not_found)
      return false
    }

    cinema = historyCinema
    return true
  }

  private fun validateRate(): Boolean {
    val rate = binding.registryRate.progress

    if (rate == 0) {
      binding.avaliacaoError.text = getString(R.string.error_invalid_rate)
      return false
    }

    binding.avaliacaoError.text = null
    return true
  }

  private fun validateSeenDate(): Boolean {
    val seenDateStr = binding.registryPickDate.text.toString()

    if (seenDateStr.isBlank()) {
      binding.pickDateError.text = getString(R.string.error_field_required)
      return false
    }

    try {
      val seenDate = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).parse(seenDateStr)

      if (seenDate.after(Date())) {
        binding.pickDateError.text = getString(R.string.error_date_in_future)
        return false
      }

      binding.pickDateError.text = null
      return true
    } catch (e: ParseException) {
      binding.pickDateError.text = getString(R.string.error_invalid_date)
      return false
    }
  }

  private fun clearForm() {
    binding.registryMovieName.text.clear()
    binding.registryMovieYear.text.clear()
    binding.registryCinema.text.clear()
    binding.registryRate.progress = 0
    binding.registryPickDate.text = getString(R.string.registry_pick_date)
    binding.registryObservations.text.clear()
    binding.registryPhotosList.adapter = null
    selectedImages.clear()
    photosClickEvent()
  }
}
