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
        // Navegación interna del menú
        setupClickListeners()
        // Navegación al Login por default
        if (savedInstanceState == null) {
            navigateTo(1)
        }
        // Tema inicial por default
        colorThemeManager()
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
            val theme = saveState.getInt("theme", 1)
            saveState.edit {
                if (theme == 1) {
                    putInt("theme", 2)
                } else {
                    putInt("theme", 1)
                }
            }
            colorThemeManager() //Aplica los cambios del tema
        }
    }

    private fun colorThemeManager(){
        val saveState = requireActivity().getSharedPreferences("saveState", Context.MODE_PRIVATE)
        val theme = saveState.getInt("theme", 1)
        if(theme == 2){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}