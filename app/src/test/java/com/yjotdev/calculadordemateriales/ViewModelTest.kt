package com.yjotdev.calculadordemateriales

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import com.yjotdev.calculadordemateriales.application.mvvm.viewmodel.UiViewModel

@OptIn(ExperimentalCoroutinesApi::class)
class ViewModelTest {

    // Configuramos el despachador de pruebas para corrutinas
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: UiViewModel

    @Before
    fun setUp() {
        // Configuramos el hilo principal para pruebas
        Dispatchers.setMain(testDispatcher)
        viewModel = UiViewModel()
    }

    @After
    fun tearDown() {
        // Reseteamos el hilo principal al finalizar
        Dispatchers.resetMain()
    }

    @Test
    fun initialStateIsEmptyUiModel() = runTest {
        val initialState = viewModel.uiState.value

        // Verifica que los valores numéricos iniciales sean 0.0f o Strings vacíos según tu modelo
        assertEquals(0f, initialState.totalBricks)
        assertEquals("", initialState.typeBrick)
        assertEquals(0f, initialState.totalTilesBoxes)
    }

    @Test
    fun calculateTotalBricksCalculatesBricksCorrectlyForSogaBond() = runTest {
        // GIVEN
        val alturaPared = 3.0f
        val largoPared = 4.0f
        val espesorJuntas = 0.015f // 1.5 cm
        val esLadrillo = true
        val esSoga = true

        // WHEN
        viewModel.calculateTotalBricks(alturaPared, largoPared, espesorJuntas, esLadrillo, esSoga)

        // THEN
        val result = viewModel.uiState.value

        // Cálculo manual esperado:
        // Area Pared = 12
        // Area Ladrillo con junta = (0.34 + 0.015) * (0.25 + 0.015) = 0.355 * 0.265 = 0.094075
        // Total = 12 / 0.094075 ≈ 127.55

        assertEquals("LADRILLOS", result.typeBrick)
        // Usamos un delta de 0.1 para diferencias de punto flotante
        assertEquals(127.55f, result.totalBricks, 0.1f)
    }

    @Test
    fun calculateTotalBricksCalculatesBlocksCorrectlyForNonSogaBond() = runTest {
        // GIVEN
        val alturaPared = 3.0f
        val largoPared = 5.0f
        val espesorJuntas = 0.01f // 1 cm
        val esLadrillo = false // Es bloque
        val esSoga = false     // No es soga (ej. Pandereta/Cabeza)

        // WHEN
        viewModel.calculateTotalBricks(alturaPared, largoPared, espesorJuntas, esLadrillo, esSoga)

        // THEN
        val result = viewModel.uiState.value

        // Cálculo esperado según lógica del ViewModel:
        // Area Pared = 15
        // Bloque no soga -> ancho=0.07, alto=0.20
        // Area unidad = (0.07 + 0.01) * (0.20 + 0.01) = 0.08 * 0.21 = 0.0168
        // Total = 15 / 0.0168 ≈ 892.85

        assertEquals("BLOQUES", result.typeBrick)
        assertEquals(892.85f, result.totalBricks, 0.1f)
    }

    @Test
    fun calculateTotalTilesCalculatesBoxesAndTilesCorrectly() = runTest {
        // GIVEN
        val m2Caja = 1.5f
        val m2Habitacion = 15.0f
        val baldosasPorCaja = 10.0f

        // WHEN
        viewModel.calculateTotalTiles(m2Caja, m2Habitacion, baldosasPorCaja)

        // THEN
        val result = viewModel.uiState.value

        // Cajas = 15 / 1.5 = 10
        assertEquals(10.0f, result.totalTilesBoxes, 0.01f)

        // Baldosas = (10 * 10).inc() => 100 + 1 (por el .inc() en tu código) = 101
        // Nota: Verifica si el .inc() es intencional para desperdicio o error, el test asume que es intencional.
        assertEquals(101.0f, result.totalTiles, 0.01f)
    }

    @Test
    fun cleanStateResetsUiStateToDefault() = runTest {
        // GIVEN - Ponemos el estado sucio primero
        viewModel.calculateTotalTiles(2f, 10f, 5f)
        assertNotEquals(0f, viewModel.uiState.value.totalTiles)

        // WHEN
        viewModel.cleanState()

        // THEN
        val cleanResult = viewModel.uiState.value
        assertEquals(0f, cleanResult.totalTiles)
        assertEquals(0f, cleanResult.totalBricks)
        assertEquals("", cleanResult.typeBrick)
    }
}
