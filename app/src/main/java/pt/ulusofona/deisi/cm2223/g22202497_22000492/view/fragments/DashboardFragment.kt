package pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments

import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.History
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemas_app.R
import pt.ulusofona.deisi.cm2223.g22202497_22000492.controller.NavigationManager
import com.example.cinemas_app.databinding.FragmentDashboardBinding
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.MovieRepository
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Movie
import pt.ulusofona.deisi.cm2223.g22202497_22000492.view.adapters.DashboardSlidersAdapter

class DashboardFragment : Fragment() {

    private val model = MovieRepository.getInstance()

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var myMovieList: List<Movie>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myMovieList = History.loadMovies(requireContext())


        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        binding = FragmentDashboardBinding.bind(view)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val top5BestRatedMovies: List<Movie> = History.top5BestRatedMovies(myMovieList)
        val top5LastSeen: List<Movie> = History.top5LastSeenMovies(myMovieList)

        val adapterTopImdb =
            DashboardSlidersAdapter(::onOperationClick, History.top5ImdbMovies(myMovieList))
        initAdapter(binding.bestMoviesRecyclerView, adapterTopImdb)

        if (top5BestRatedMovies.isEmpty()) {
            binding.bestRatedLayout.visibility = View.GONE
        } else {
            binding.bestRatedLayout.visibility = View.VISIBLE
            val adapterTopRated = DashboardSlidersAdapter(::onOperationClick, top5BestRatedMovies)
            initAdapter(binding.recentMoviesRecyclerView, adapterTopRated)
        }

        if (top5LastSeen.isEmpty()) {
            binding.lastSeenLayout.visibility = View.GONE
        } else {
            binding.lastSeenLayout.visibility = View.VISIBLE
            val adapterLastSeenMovies = DashboardSlidersAdapter(::onOperationClick, top5LastSeen)
            initAdapter(binding.lastSeenMoviesRecyclerView, adapterLastSeenMovies)
        }
    }

    private fun initAdapter(view: RecyclerView, adapter: DashboardSlidersAdapter) {
        view.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        view.adapter = adapter
    }

    private fun onOperationClick(uuid: String) {
        NavigationManager.goToFilmesDetailFragment(parentFragmentManager, uuid)
    }
}
