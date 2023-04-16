package com.example.cinemas_app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinemas_app.R
import com.example.cinemas_app.databinding.FragmentFilmesDetailBinding

private const val ARG_OPERATION_UUID = "ARG_OPERATION_UUID"

class FilmesDetailFragment : Fragment() {

    private lateinit var binding: FragmentFilmesDetailBinding
    private var operationUuid: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            operationUuid = it.getString(ARG_OPERATION_UUID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_filmes_detail, container, false)
        binding = FragmentFilmesDetailBinding.bind(view)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        operationUuid?.let { uuid ->
            val operation = History.getOperationById(uuid)
            operation?.let { placeData(it) }
        }
    }

    private fun placeData(ui: Filmes) {
    //  binding.tvExpression.text = ui.expression
    //  binding.tvResult.text = ui.result
    //  binding.tvTimestamp.text = ui.timestamp.toString()
    //  binding.tvUuid.text = ui.uuid
    }

    companion object {

        @JvmStatic
        fun newInstance(uuid: String) =
            FilmesDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_OPERATION_UUID, uuid)
                }
            }
    }

}