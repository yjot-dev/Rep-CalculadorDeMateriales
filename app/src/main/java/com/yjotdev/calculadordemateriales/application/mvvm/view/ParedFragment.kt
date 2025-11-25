package com.yjotdev.calculadordemateriales.application.mvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.yjotdev.calculadordemateriales.R
import com.yjotdev.calculadordemateriales.application.mvvm.viewmodel.UiViewModel
import com.yjotdev.calculadordemateriales.databinding.FragmentParedBinding

@AndroidEntryPoint
class ParedFragment : Fragment() {

    private val viewModel: UiViewModel by activityViewModels()
    private lateinit var binding: FragmentParedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentParedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeViewModelState()
    }

    private fun setupClickListeners(){
        binding.btnCalcular.setOnClickListener{
            val alturaPared = binding.editAltura.text.toString()
            val largoPared = binding.editLargo.text.toString()
            val espesorJuntas = binding.editEspesorJuntas.text.toString()
            val isBrick = binding.rbLadrillo.isChecked
            val isRopeRigging = binding.rbAparejoSoga.isChecked

            if(alturaPared.isNotEmpty() && largoPared.isNotEmpty() && espesorJuntas.isNotEmpty()){
                viewModel.calculateTotalBricks(
                    alturaDePared = alturaPared.toFloat(),
                    largoDePared = largoPared.toFloat(),
                    espesorDeJuntas = espesorJuntas.toFloat(),
                    esLadrillo = isBrick,
                    esAparejoSoga = isRopeRigging
                )
            }else{
                Toast.makeText(this.context, "EXISTEN CAMPOS VACIOS", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeViewModelState() {
        binding.rbLadrillo.isChecked = true
        binding.rbAparejoSoga.isChecked = true
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    if(uiState.totalBricks > 0f && uiState.typeBrick.isNotEmpty()){
                        binding.result.text = resources.getString(
                            R.string.result_uno,
                            uiState.totalBricks,
                            uiState.typeBrick
                        )
                    }
                }
            }
        }
    }
}