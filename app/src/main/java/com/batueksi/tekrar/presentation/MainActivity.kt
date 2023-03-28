package com.batueksi.tekrar.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.batueksi.tekrar.R
import com.batueksi.tekrar.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHost.navController
        val popUpMenu = PopupMenu(this, null)
        popUpMenu.inflate(R.menu.bottom_nav_menu)

        binding.bottomNavigation.setupWithNavController(popUpMenu.menu, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->

            val isVisibleBottomBar = when (destination.id) {
                R.id.homeFragment2 -> true
                R.id.settingsFragment2 -> true
                R.id.searchFragment -> true

                else -> false
            }

            binding.bottomNavigation.isVisible = isVisibleBottomBar
        }

    }
}