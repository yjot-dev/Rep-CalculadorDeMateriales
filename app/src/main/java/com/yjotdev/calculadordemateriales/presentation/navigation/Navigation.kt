package com.yjotdev.calculadordemateriales.presentation.navigation

import android.graphics.Color
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.yjotdev.calculadordemateriales.MainActivity
import com.yjotdev.calculadordemateriales.R

fun MainActivity.setupNavigation() {
    // 1. Configurar Edge-to-Edge
    enableEdgeToEdge(
        statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
        navigationBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)
    )
    WindowCompat.getInsetsController(window, window.decorView).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
    // 2. Configurar el NavController
    val navHostFragment = supportFragmentManager
        .findFragmentById(R.id.fragmentNav) as NavHostFragment
    val navController = navHostFragment.navController
    // 3. Vincular NavController con el BottomMenu
    binding.bottomMenu.setupWithNavController(navController)
}