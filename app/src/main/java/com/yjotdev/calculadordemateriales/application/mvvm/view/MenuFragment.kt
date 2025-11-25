package com.yjotdev.calculadordemateriales.application.mvvm.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import dagger.hilt.android.AndroidEntryPoint
import com.yjotdev.calculadordemateriales.R
import com.yjotdev.calculadordemateriales.application.navigation.Navigation
import com.yjotdev.calculadordemateriales.databinding.FragmentMenuBinding

@AndroidEntryPoint
class MenuFragment : Fragment(), Navigation {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Aplicar tema
        colorThemeManager()
        // Navegación interna del menú
        setupClickListeners()
        // Navegación al Login por default
        if (savedInstanceState == null) {
            navigateTo(1)
        }
    }

    override fun navigateTo(destination: Int) {
        val fragmentManager = childFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragmentToShow = when (destination) {
            1 -> ParedFragment()
            2 -> PisoFragment()
            else -> null
        }
        fragmentToShow?.let {
            transaction.replace(R.id.fragmentAdmin, it)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun setupClickListeners(){
        binding.btnPared.setOnClickListener { navigateTo(1) }
        binding.btnPiso.setOnClickListener { navigateTo(2) }
        binding.btnModo.setOnClickListener {
            val saveState = requireActivity().getSharedPreferences("saveState", Context.MODE_PRIVATE)
            //Detecta el modo actual del app
            val currentNightMode = AppCompatDelegate.getDefaultNightMode()
            //Logica de cambio de modo
            val newMode = if (currentNightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.MODE_NIGHT_NO
            } else {
                AppCompatDelegate.MODE_NIGHT_YES
            }
            //Guarda preferencia
            saveState.edit {
                putInt("theme", newMode)
            }
            //Aplica los cambios
            AppCompatDelegate.setDefaultNightMode(newMode)
        }
    }

    private fun colorThemeManager(){
        val saveState = requireActivity().getSharedPreferences("saveState", Context.MODE_PRIVATE)
        //Recupera preferencia
        val savedMode = saveState.getInt("theme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        AppCompatDelegate.setDefaultNightMode(savedMode)
    }
}