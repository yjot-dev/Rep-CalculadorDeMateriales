package com.yjotdev.calculadordemateriales.presentation.mvvm.state

data class UiState(
    // Resultados para ParedFragment
    val totalBricks: Float = 0f, // total de ladrillos o bloques
    val typeBrick: String = "", // tipo de ladrillo o bloque
    // Resultados para PisoFragment
    val totalTiles: Float = 0f, // total de baldosas
    val totalTilesBoxes: Float = 0f // total de cajas de baldosas
)