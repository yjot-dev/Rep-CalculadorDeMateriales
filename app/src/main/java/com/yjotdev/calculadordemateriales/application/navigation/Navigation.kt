package com.yjotdev.calculadordemateriales.application.navigation

import android.content.Context.MODE_PRIVATE
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.yjotdev.calculadordemateriales.MainActivity
import com.yjotdev.calculadordemateriales.R

fun MainActivity.setupAppNavigation() {
    // Inicializa el NavController
    val navHostFragment = supportFragmentManager
        .findFragmentById(R.id.fragmentNav) as NavHostFragment
    val navController = navHostFragment.navController
    // Vincula NavController con el BottomMenu
    binding.bottomMenu.setupWithNavController(navController)
    // Logica del BottomMenu para el boton tel tema
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
    val saveState = this.getSharedPreferences("saveState", MODE_PRIVATE)
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
    val saveState = this.getSharedPreferences("saveState", MODE_PRIVATE)
    //Recupera preferencia
    val savedMode = saveState.getInt("theme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    AppCompatDelegate.setDefaultNightMode(savedMode)
}