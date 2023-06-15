package pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemas_app.R
import pt.ulusofona.deisi.cm2223.g22202497_22000492.controller.NavigationManager
import com.example.cinemas_app.databinding.FragmentMovieListBinding
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.MovieRepository
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.MovieRegistry
import pt.ulusofona.deisi.cm2223.g22202497_22000492.view.adapters.MovieAdapter


class MovieListFragment(private val topFragmentManager: FragmentManager) : Fragment() {
  private lateinit var adapter : MovieAdapter
  private lateinit var binding: FragmentMovieListBinding
  private lateinit var myMovieList: List<MovieRegistry>


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    myMovieList = emptyList()
    adapter = MovieAdapter(::onOperationClick, myMovieList)


    MovieRepository.getInstance().getMoviesList { result ->
      result.getOrNull().let {
        if (it != null) {
          myMovieList = it
          adapter.setMovieList(myMovieList)
        }
      }
    }

    val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
    binding = FragmentMovieListBinding.bind(view)
    binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
    binding.rvHistory.adapter = adapter


    binding.button.setOnClickListener {
      clickButtonSort()
      adapter.setMovieList(myMovieList)
    }

    return binding.root
  }



  private fun onOperationClick(uuid: String) {
    //Aqui, primeiro obtemos o índice do filme atual na lista usando indexOfFirst. Em seguida, verificamos se o índice é menor que o tamanho da lista menos um para garantir que ainda haja um próximo filme na lista. Se houver, definimos o próximo índice como currentIndex + 1. Caso contrário, o próximo índice é definido como 0 para ir para o primeiro filme da lista.

    //Em seguida, obtemos o próximo filme com base no próximo índice e navegamos para os detalhes desse filme usando NavigationManager.goToFilmesDetailFragment.

    //Dessa forma, ao clicar em um item da lista, ele irá para os detalhes do filme seguinte. Se for o último filme da lista, irá para os detalhes do primeiro filme.

      // val currentIndex = myMovieList.indexOfFirst { it.id.toString() == uuid }

    // val nextIndex = if (currentIndex < myMovieList.size - 1) {
    //   currentIndex + 1
    //  } else {
    //   0
    //  }

    //  val nextMovie = myMovieList[nextIndex]
    //  NavigationManager.goToFilmesDetailFragment(topFragmentManager, nextMovie.id.toString())




    NavigationManager.goToFilmesDetailFragment(topFragmentManager, uuid)
  }

  private fun clickButtonSort() { //os filmes serão organizados em ordem ascendente, começando do valor mais baixo (1) para o valor mais alto (3).
    myMovieList = myMovieList.sortedBy { it.movie.imdbRating }
    adapter.setMovieList(myMovieList)
  }

  // private var isAscendingOrder = true

  // private fun clickButtonSort() {
    // Inverte a ordem ao clicar no botão
  // isAscendingOrder = !isAscendingOrder

    // Ordena a lista de filmes com base no imdbRating
  // myMovieList = if (isAscendingOrder) {
  // myMovieList.sortedBy { it.movie.imdbRating }
  //  } else {
  //  myMovieList.sortedByDescending { it.movie.imdbRating }
  //  }

    // Atualiza o adaptador com a nova lista ordenada
  // adapter.setMovieList(myMovieList)
  // }


}
