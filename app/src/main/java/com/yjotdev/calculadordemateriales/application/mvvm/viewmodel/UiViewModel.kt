package com.yjotdev.calculadordemateriales.application.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import com.yjotdev.calculadordemateriales.application.mvvm.model.UiModel

@HiltViewModel
class UiViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(UiModel())
    val uiState: StateFlow<UiModel> = _uiState.asStateFlow()

    override fun onCleared() {
        super.onCleared()
        cleanState()
    }
    /**
     * Limpia el estado del ViewModel
     **/
    fun cleanState() {
        _uiState.value = UiModel()
    }
    /**
     * Calcula la cantidad de ladrillos o bloques necesarios
     **/
    fun calculateTotalBricks(
        alturaDePared: Float,
        largoDePared: Float,
        espesorDeJuntas: Float,
        esLadrillo: Boolean,
        esAparejoSoga: Boolean
    ){
        var alturaLadrillo: Float
        var largoLadrillo: Float
        var anchoLadrillo: Float
        var nombreObjeto = ""
        var totalCantidadDeLadrillos = 0f

        if(esLadrillo){
            nombreObjeto = "LADRILLOS"
            if(esAparejoSoga){
                alturaLadrillo = 0.25f
                largoLadrillo = 0.34f
                totalCantidadDeLadrillos = (1/((largoLadrillo+espesorDeJuntas)*(alturaLadrillo+espesorDeJuntas))) * (alturaDePared*largoDePared)
            }
            else{
                alturaLadrillo = 0.25f
                anchoLadrillo = 0.10f
                totalCantidadDeLadrillos = (1/((anchoLadrillo+espesorDeJuntas)*(alturaLadrillo+espesorDeJuntas))) * (alturaDePared*largoDePared)
            }
        }
        else{
            nombreObjeto = "BLOQUES"
            if(esAparejoSoga){
                alturaLadrillo = 0.20f
                largoLadrillo = 0.40f
                totalCantidadDeLadrillos = (1/((largoLadrillo+espesorDeJuntas)*(alturaLadrillo+espesorDeJuntas))) * (alturaDePared*largoDePared)
            }
            else{
                alturaLadrillo = 0.20f
                anchoLadrillo = 0.07f
                totalCantidadDeLadrillos = (1/((anchoLadrillo+espesorDeJuntas)*(alturaLadrillo+espesorDeJuntas))) * (alturaDePared*largoDePared)
            }
        }

        _uiState.update { currentState ->
            currentState.copy(
                totalBricks = totalCantidadDeLadrillos,
                typeBrick = nombreObjeto
            )
        }
    }
    /**
     * Calcula la cantidad de baldosas necesarias
     **/
    fun calculateTotalTiles(
        m2DeCajaDeBaldosa: Float,
        m2DeLaHabitacion: Float,
        baldosasPorCaja: Float
    ){
        val totalCantidadDeCajasDeBaldosa = m2DeLaHabitacion / m2DeCajaDeBaldosa
        val totalCantidadDeBaldosas = (totalCantidadDeCajasDeBaldosa * baldosasPorCaja).inc()

        _uiState.update { currentState ->
            currentState.copy(
                totalTilesBoxes = totalCantidadDeCajasDeBaldosa,
                totalTiles = totalCantidadDeBaldosas
            )
        }
    }
}