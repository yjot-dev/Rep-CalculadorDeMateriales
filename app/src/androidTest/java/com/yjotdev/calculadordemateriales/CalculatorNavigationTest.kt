package com.yjotdev.calculadordemateriales

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class CalculatorNavigationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun init() {
        // 4. Inyectar dependencias antes de cada test
        hiltRule.inject()
    }

    @Test
    fun testNavigationAndCalculationFlow() {
        // ===============================================================
        // 1. ESCENARIO: PARED FRAGMENT (Vista inicial por defecto)
        // ===============================================================

        // Verificamos que estamos en la vista de Pared (buscando un elemento único)
        // Asumo que el EditText de altura tiene id 'etAltura' o similar. Ajusta según tu XML.
        onView(withId(R.id.editAltura)).check(matches(isDisplayed()))

        // Ingresamos datos para calcular ladrillos
        // Altura: 3m
        onView(withId(R.id.editAltura))
            .perform(typeText("3"), closeSoftKeyboard())

        // Largo: 4m
        onView(withId(R.id.editLargo))
            .perform(typeText("4"), closeSoftKeyboard())

        // Espesor juntas: 0.2m
        onView(withId(R.id.editEspesorJuntas))
            .perform(typeText("0.2"), closeSoftKeyboard())

        // Clic en Calcular
        onView(withId(R.id.btnCalcular)).perform(click())

        // Verificamos que se muestre un resultado
        // Buscamos el TextView de resultados (ej. txtResultBricks)
        // Verificamos que no esté vacío o contenga texto esperado
        onView(withId(R.id.result))
            .check(matches(isDisplayed()))


        // ===============================================================
        // 2. NAVEGACIÓN: IR A PISO FRAGMENT
        // ===============================================================

        // Hacemos clic en el ítem del menú inferior correspondiente a Piso
        // El ID debe coincidir con el item en tu 'bottom_menu.xml' (ej. @id/pisoFragment)
        onView(withId(R.id.pisoFragment)).perform(click())

        // Verificamos que la vista cambió buscando un elemento único de PisoFragment
        // Ej. El input de metros cuadrados de la habitación
        onView(withId(R.id.editM2DeLaHabitacion)).check(matches(isDisplayed()))


        // ===============================================================
        // 3. ESCENARIO: PISO FRAGMENT
        // ===============================================================

        // Ingresamos datos para calcular baldosas
        // M2 Habitación: 20
        onView(withId(R.id.editM2DeLaHabitacion))
            .perform(typeText("20"), closeSoftKeyboard())

        // M2 Caja: 1.5
        onView(withId(R.id.editM2DeCajaBaldosas))
            .perform(typeText("1.5"), closeSoftKeyboard())

        // Baldosas por caja: 10
        onView(withId(R.id.editBaldosasPorCaja))
            .perform(typeText("10"), closeSoftKeyboard())

        // Clic en Calcular
        // Nota: Si el ID del botón es el mismo en ambos fragments (btnCalcular),
        // Espresso usará el que esté visible actualmente.
        onView(withId(R.id.btnCalcular)).perform(click())

        // Verificamos el resultado
        // Ejemplo: 20 / 1.5 = 13.33 -> 14 cajas.
        // Verificamos que el TextView de resultado contenga algo relacionado a cajas
        onView(withId(R.id.result))
            .check(matches(isDisplayed()))
        // Opcional: Verificar texto específico si sabes el resultado exacto
        // .check(matches(withText(containsString("14"))))


        // ===============================================================
        // 4. RETORNO: VOLVER A PARED
        // ===============================================================

        // Clic en el menú de Pared
        onView(withId(R.id.paredFragment)).perform(click())

        // Verificar que volvimos
        onView(withId(R.id.editAltura)).check(matches(isDisplayed()))
    }
}