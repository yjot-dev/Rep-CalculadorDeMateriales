package com.yjotdev.calculadordemateriales.presentation.navigation

import android.content.Context.MODE_PRIVATE
import android.graphics.Color
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
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
    // 4. Cambio de tema haciendo clic en el ícono de modo
    binding.bottomMenu.setOnItemSelectedListener { item ->
        if (item.itemId == R.id.btnModo) {
            toggleTheme()
            false
        } else {
            NavigationUI.onNavDestinationSelected(item, navController)
            true
        }
    }
}

fun MainActivity.toggleTheme() {
    val context = this@toggleTheme
    val saveState = context.getSharedPreferences("saveState", MODE_PRIVATE)
    val currentNightMode = AppCompatDelegate.getDefaultNightMode()
    val newMode = if (currentNightMode == AppCompatDelegate.MODE_NIGHT_YES) {
        AppCompatDelegate.MODE_NIGHT_NO
    } else {
        AppCompatDelegate.MODE_NIGHT_YES
    }
    saveState.edit {
        putInt("theme", newMode)
    }
    AppCompatDelegate.setDefaultNightMode(newMode)
}

fun MainActivity.colorThemeManager() {
    val context = this@colorThemeManager
    val saveState = context.getSharedPreferences("saveState", MODE_PRIVATE)
    //Recupera preferencia
    val savedMode = saveState.getInt("theme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    AppCompatDelegate.setDefaultNightMode(savedMode)
}