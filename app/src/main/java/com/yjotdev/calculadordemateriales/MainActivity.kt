package com.yjotdev.calculadordemateriales

import dagger.hilt.android.AndroidEntryPoint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yjotdev.calculadordemateriales.presentation.navigation.colorThemeManager
import com.yjotdev.calculadordemateriales.presentation.navigation.setupNavigation
import com.yjotdev.calculadordemateriales.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    internal lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configura la IU de la actividad
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Configura la navegación, bottomMenu y edge-to-edge
        setupNavigation()
        // Aplicar tema
        colorThemeManager()
    }
}