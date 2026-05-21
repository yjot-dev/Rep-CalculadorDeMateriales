package com.yjotdev.calculadordemateriales.presentation.mvvm.ui

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
import kotlin.getValue
import com.yjotdev.calculadordemateriales.presentation.mvvm.viewmodel.UiViewModel
import com.yjotdev.calculadordemateriales.databinding.FragmentPisoBinding
import com.yjotdev.calculadordemateriales.R
import com.yjotdev.calculadordemateriales.presentation.utils.Helper

@AndroidEntryPoint
class PisoFragment : Fragment() {

    private val viewModel: UiViewModel by activityViewModels()
    private lateinit var binding: FragmentPisoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPisoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeViewModelState()
    }

    private fun setupClickListeners(){
        binding.btnCalcular.setOnClickListener{
            val m2DeCajaDeBaldosa = binding.editM2DeCajaBaldosas.text.toString()
            val m2DeLaHabitacion = binding.editM2DeLaHabitacion.text.toString()
            val baldosasPorCaja = binding.editBaldosasPorCaja.text.toString()

            context?.let { context ->
                if(m2DeCajaDeBaldosa.isNotEmpty() && m2DeLaHabitacion.isNotEmpty() && baldosasPorCaja.isNotEmpty()){
                    if (Helper.isValidNumber(m2DeCajaDeBaldosa)
                        && Helper.isValidNumber(m2DeLaHabitacion)
                        && Helper.isValidNumber(baldosasPorCaja)) {
                        viewModel.calculateTotalTiles(
                            m2DeCajaDeBaldosa = m2DeCajaDeBaldosa.toFloat(),
                            m2DeLaHabitacion = m2DeLaHabitacion.toFloat(),
                            baldosasPorCaja = baldosasPorCaja.toFloat()
                        )
                    }else {
                        Toast.makeText(context, R.string.toast_invalid_data, Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(context, R.string.toast_empty_fields, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun observeViewModelState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    if(uiState.totalTiles > 0f && uiState.totalTilesBoxes > 0f){
                        binding.result.text = resources.getString(R.string.result_dos,
                            uiState.totalTilesBoxes,
                            uiState.totalTiles
                        )
                    }
                }
            }
        }
    }
}