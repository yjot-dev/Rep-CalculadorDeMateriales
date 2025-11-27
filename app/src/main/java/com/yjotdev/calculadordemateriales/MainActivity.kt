package com.yjotdev.calculadordemateriales

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.os.Build
import android.view.WindowManager
import androidx.activity.SystemBarStyle
import androidx.core.view.WindowCompat
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import com.yjotdev.calculadordemateriales.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Aplicar tema
        colorThemeManager()
        // Ajusta la vista a toda la pantalla
        viewEdgeToEdge()
        // Configura la IU de la actividad
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Inicializa el NavController
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentNav) as NavHostFragment
        navController = navHostFragment.navController
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

    private fun toggleTheme() {
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

    private fun colorThemeManager(){
        val saveState = this.getSharedPreferences("saveState", MODE_PRIVATE)
        //Recupera preferencia
        val savedMode = saveState.getInt("theme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        AppCompatDelegate.setDefaultNightMode(savedMode)
    }

    private fun viewEdgeToEdge(){
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            )
        )
        WindowCompat.getInsetsController(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
    }
}