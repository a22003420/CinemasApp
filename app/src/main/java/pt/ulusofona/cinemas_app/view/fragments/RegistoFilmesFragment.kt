package pt.ulusofona.cinemas_app.view.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cinemas_app.R
import com.example.cinemas_app.databinding.FragmentRegistoFilmesBinding
import pt.ulusofona.cinemas_app.model.Filme
import pt.ulusofona.cinemas_app.model.History
import pt.ulusofona.cinemas_app.model.Movie
import pt.ulusofona.cinemas_app.model.MovieRegistry
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class RegistoFilmesFragment : Fragment() {

  private lateinit var binding: FragmentRegistoFilmesBinding
  private var movieRegistry: MovieRegistry = MovieRegistry()
  private var movieList: List<Movie> = listOf()
  private var movie: Movie? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    val view = inflater.inflate(R.layout.fragment_registo_filmes, container, false)
    binding = FragmentRegistoFilmesBinding.bind(view)
    movieList = History.loadMovies(requireContext())
    pickDateClickEvent()
    saveClickEvent()
    return binding.root
  }

  private fun pickDateClickEvent() {
    binding.registryPickDate.setOnClickListener {
      // Get the current date
      val calendar = Calendar.getInstance()
      val year = calendar.get(Calendar.YEAR)
      val month = calendar.get(Calendar.MONTH)
      val day = calendar.get(Calendar.DAY_OF_MONTH)

      // Show the date picker dialog
      val datePicker = DatePickerDialog(requireContext(), R.style.RedCalendar,
        { _, year, month, day ->
        // Handle the selected date
        val selectedDate = "$day/${month + 1}/$year"
        // Do something with the selected date, e.g. update a TextView
        binding.registryPickDate.text = selectedDate
      }, year, month, day)
      datePicker.show()
    }
  }

  private fun saveClickEvent() {
    binding.registrySaveButton.setOnClickListener {
      if (validateInputs() && movie != null) {
        val movieId = movie!!.getId()
        val cinema = binding.registryCinema.text.toString()
        val rate = binding.registryRate.progress
        val seenDate = binding.registryPickDate.text.toString()
        val observations = binding.registryObservations.text.toString()

        movieRegistry = MovieRegistry(movieId, cinema, rate, seenDate, observations)
        displayConfirm()
      } else {
        Toast.makeText(requireContext(), getString(R.string.registry_error), Toast.LENGTH_SHORT).show()
      }
    }
  }

  private fun displayConfirm() {
    val confirmDialog = AlertDialog.Builder(requireContext())
      .setTitle(getString(R.string.dialog_confirm))
      .setMessage(getString(R.string.registry_dialog_message, movie?.getName(), movieRegistry.getRate().toString()))
      .setPositiveButton(getString(R.string.dialog_save)) { _, _ ->
        History.saveRegistry(movieRegistry)

        Toast.makeText(requireContext(), getString(R.string.registry_success),
          Toast.LENGTH_SHORT).show()
      }
      .setNegativeButton(getString(R.string.dialog_cancel), null)
      .create()
    confirmDialog.show()
  }

  private fun validateInputs(): Boolean {
    return validateMovieName() &&
           validateCinema() &&
           validateRate() &&
           validateSeenDate()
  }

  private fun validateMovieName() : Boolean {
    val movieName = binding.registryMovieName.text.toString()

    if (movieName.isBlank()) {
      binding.registryCinema.error = getString(R.string.error_field_required)
      return false
    }

    movie = History.getMovieByName(movieList, movieName)

    if (movie == null) {
      binding.registryCinema.error = getString(R.string.error_invalid_movie)
      return false
    }

    return true
  }
  private fun validateCinema(): Boolean {
    if (binding.registryCinema.text.toString().isBlank()) {
      binding.registryCinema.error = getString(R.string.error_field_required)
      return false
    }
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
}
