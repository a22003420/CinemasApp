package pt.ulusofona.deisi.cm2223.g22202497_22000492.controller

import android.app.Dialog
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import com.example.cinemas_app.R
import com.example.cinemas_app.databinding.ActivityMainBinding
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.extension.send
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.MovieRepository
import java.util.*


class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  private lateinit var searchVoiceDialog: Dialog
  private lateinit var resultTextView: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    // Cria o diálogo para busca por voz
    searchVoiceDialog = createCountdownDialog()
    // Verifica as permissões necessárias antes de prosseguir para a Dashboard
    permissionsBuilder(
      Manifest.permission.ACCESS_FINE_LOCATION,
      Manifest.permission.ACCESS_COARSE_LOCATION,
      Manifest.permission.RECORD_AUDIO).build().send { result ->
      // Navega para o fragmento de Dashboard
        NavigationManager.goToDashboardFragment(
          supportFragmentManager,
          binding.bottomNavigationView,
          resources.getColor(R.color.wine_red, null)
        )
    }
  }

  override fun onStart() {
    super.onStart()
    setupBottomMenu()
  }

  override fun onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig)
  }

  // Configura o menu inferior
  private fun setupBottomMenu() {
    binding.bottomNavigationView.setOnItemSelectedListener{
      onClickNavigationItem(it)
    }
  }



  // Lida com o clique nos itens do menu inferior
  private fun onClickNavigationItem(item: MenuItem): Boolean {
    val highlightedColor = resources.getColor(R.color.wine_red, null)
    when(item.itemId) {
      R.id.navigation_dashboard -> {
        // Navega para o fragmento da Dashboard
        NavigationManager.goToDashboardFragment(
          supportFragmentManager, binding.bottomNavigationView, highlightedColor
        )
      }
      R.id.navigation_movie_presentation -> {
        // Navega para o fragmento de Filmes
        NavigationManager.goToFilmesFragment(
          supportFragmentManager, binding.bottomNavigationView, highlightedColor
        )
      }
      R.id.navigation_movie_register -> {
        // Navega para o fragmento de Registro de Film
        NavigationManager.goToRegistoFragment(
          supportFragmentManager, binding.bottomNavigationView, highlightedColor
        )
      }
      R.id.navigation_microphone -> {
        // Abre o diálogo de busca por voz
        searchVoiceDialog.show()
      }
    }
    return true
  }

  // Cria o diálogo de busca por voz
  private fun createCountdownDialog(): Dialog {
    val dialog = Dialog(this)
    dialog.setContentView(R.layout.voice_search_dialog)

    resultTextView = dialog.findViewById<TextView>(R.id.search_text)
    val startButton = dialog.findViewById<TextView>(R.id.start_button)
    val searchButton = dialog.findViewById<TextView>(R.id.search_button)

    startButton.setOnClickListener {
      // Solicita permissão para gravar áudio
      val permission = Manifest.permission.RECORD_AUDIO
      val requestCode = 1
      if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
      } else {
        // Inicia o reconhecimento de voz
        startVoiceRecognition(resultTextView)
      }
    }

    searchButton.setOnClickListener {
      val searchText = resultTextView.text.toString()
      if (searchText.isEmpty()) {
        // Exibe uma mensagem se o texto de busca estiver vazio
        Toast.makeText(this, getString(R.string.search_text_empty), Toast.LENGTH_SHORT).show()
      }
      else {
        // Obtém o registro do filme com base no texto de busca
        MovieRepository.getInstance().getRegistryByMovieName(searchText) { result ->
          if (result.isSuccess) {
            result.getOrNull()?.let { r ->
              NavigationManager.goToFilmesDetailFragment(fm = supportFragmentManager, r.id.toString())
            }
          }
          else {
            result.exceptionOrNull()?.let {
              CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
              }
            }
          }
        }
      }

      dialog.dismiss()
    }

    return dialog
  }
  // Responsável por iniciar a atividade de reconhecimento de fala e receber o resultado
  private val speechRecognitionLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
    if (result.resultCode == Activity.RESULT_OK && result.data != null) {
      val data = result.data
      val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
      val spokenText = results?.get(0)
      resultTextView.text = spokenText
    }
  }

  // Inicia o reconhecimento de voz
  private fun startVoiceRecognition(textView: TextView) {
    resultTextView = textView
    val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
    speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
    speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speak_now))
    // Inicia a atividade de reconhecimento de fala
    speechRecognitionLauncher.launch(speechRecognizerIntent)
  }




}
