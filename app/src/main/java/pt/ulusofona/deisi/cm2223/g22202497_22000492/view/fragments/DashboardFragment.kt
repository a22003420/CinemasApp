package pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments

import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.History
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemas_app.R
import pt.ulusofona.deisi.cm2223.g22202497_22000492.controller.NavigationManager
import com.example.cinemas_app.databinding.FragmentDashboardBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.MovieRepository
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Cinema
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Movie
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.MovieRegistry
import pt.ulusofona.deisi.cm2223.g22202497_22000492.view.adapters.DashboardSlidersAdapter
import java.math.BigDecimal
import java.math.RoundingMode

class DashboardFragment : Fragment() {

  private val model = MovieRepository.getInstance()

  private lateinit var binding: FragmentDashboardBinding
  private lateinit var myRegistryList: List<MovieRegistry>
  private lateinit var myCinemaList: List<Cinema>
  private lateinit var mapFragment: MapFragment

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    myRegistryList = emptyList()
    myCinemaList = emptyList()
    mapFragment = MapFragment()


    MovieRepository.getInstance().getMoviesList { result ->
      if(result.isSuccess) {
        result.getOrNull().let {
          if (it != null) {
            myRegistryList = it
          }

          MovieRepository.getInstance().getCinemasList { result ->
            result.getOrNull().let {
              if (it != null) {
                myCinemaList = it
              }

              CoroutineScope(Dispatchers.Main).launch {
                setStats()
              }
            }
          }
        }
      }
    }


    val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
    binding = FragmentDashboardBinding.bind(view)

    val fragmentManager: FragmentManager = childFragmentManager
    val transaction: FragmentTransaction = fragmentManager.beginTransaction()
    transaction.replace(R.id.map_container, mapFragment)
    transaction.commit()

    return binding.root
  }

  private fun setStats() {
    var averageRate = 0.0

    if (myRegistryList.size > 0) {
      averageRate = BigDecimal( myRegistryList.map { it.rate }.average())
        .setScale(2, RoundingMode.HALF_UP).toDouble()
    }

    val highestRated = myRegistryList.maxByOrNull { it.rate }
    val totalMovies = myRegistryList.size
    val totalCinemas = myCinemaList.size

    binding.averageRate.text = averageRate.toString()
    binding.moviesSeen.text = totalMovies.toString()
    binding.cinemasVisited.text = totalCinemas.toString()
    binding.highestScore.text = if (highestRated !== null) highestRated.rate.toString() else "0"
  }

  private fun onResponseReceived (movieList: List<Movie>) {
    // Processar a lista de filmes recebidos

    val totalMovies = movieList.size
    val toastMessage = "Foram descarregados $totalMovies filmes"
    Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()

  }

}
