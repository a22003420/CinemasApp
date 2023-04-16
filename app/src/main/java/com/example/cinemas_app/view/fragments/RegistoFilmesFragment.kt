package com.example.cinemas_app.view.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cinemas_app.R
import com.example.cinemas_app.databinding.FragmentRegistoFilmesBinding
import com.example.cinemas_app.view.Filmes
import com.example.cinemas_app.view.History
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class RegistoFilmesFragment : Fragment() {

    private lateinit var binding: FragmentRegistoFilmesBinding
    private var selectedDate = Calendar.getInstance()
    var contador = 3

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_registo_filmes, container, false)
        binding = FragmentRegistoFilmesBinding.bind(view)
        return binding.root
    }

    private fun updateDateField() {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        binding.editTextData.setText(dateFormat.format(selectedDate.time))
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onStart() {
        super.onStart()

        binding.editTextData.setOnClickListener {
            val datePicker = DatePickerDialog(requireContext(),R.style.RedCalendar,
                { _, year, month, dayOfMonth ->
                    selectedDate.set(year, month, dayOfMonth)
                    updateDateField()
                },
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        binding.buttonGuardar.setOnClickListener {
            if (validateInputs()) {
                val nome = binding.editTextNomeFilme.text.toString()
                val cinema = binding.editTextCinema.text.toString()
                val classificacao = binding.seekBarAvaliacao.progress
                val dataString = binding.editTextData.text.toString()
                //val imagem = resources.getDrawable(R.drawable.null_image)
                val imagem = binding.editTextObservacoes.text.toString()
                val observacoes = binding.editTextObservacoes.text.toString()

                val data = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).parse(dataString)
                val filme = Filmes(UUID.randomUUID().toString(), nome, cinema, classificacao, data, imagem, observacoes)

                val confirmDialog = AlertDialog.Builder(requireContext())
                    .setTitle("Confirmar")
                    .setMessage("Deseja guardar o filme $nome?")
                    .setPositiveButton("Guardar") { _, _ ->
                        History.historyItems.add(filme)
                        contador++ // Incrementa o contador, para substituir
                                    // o UUID.randomUUID().toString(),
                        println("Já tem $contador Filmes na Lista de Filmes")
                        // Exibe mensagem de sucesso
                        Toast.makeText(requireContext(), "Filme guardado com sucesso!",
                            Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("Cancelar", null)
                    .create()
                confirmDialog.show()
            } else {
                // Exibe mensagem de erro
                Toast.makeText(requireContext(), "Por favor, preencha todos os campos " +
                        "corretamente.", Toast.LENGTH_SHORT).show()
            }
        }



    }


    private fun validateInputs(): Boolean {
        var isValid = true
        val nome = binding.editTextNomeFilme.text.toString()
        val cinema = binding.editTextCinema.text.toString()
        val classificacao = binding.seekBarAvaliacao.progress
        val data = binding.editTextData.text.toString()
        //val imagem = resources.getDrawable(R.drawable.null_image)
        val imagem = binding.editTextObservacoes.text.toString()
        val observacoes = binding.editTextObservacoes.text.toString()

        if (nome.isBlank()) {
            binding.editTextNomeFilme.error = "Campo obrigatório"
            isValid = false
        }
        if (cinema.isBlank()) {
            binding.editTextCinema.error = "Campo obrigatório"
            isValid = false
        }
        if (data.isBlank()) {
            binding.editTextData.error = "Campo obrigatório"
            isValid = false
        } else {
            try {
                SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).parse(data)
                if (selectedDate.after(Calendar.getInstance())) {
                    binding.editTextData.error = "Data inválida"
                    isValid = false
                }
            } catch (e: ParseException) {
                binding.editTextData.error = "Data inválida"
                isValid = false
            }

        }

        if (observacoes.isBlank()) {
            binding.editTextObservacoes.error = "Campo obrigatório"
            isValid = false
        }

        return isValid
    }




}
