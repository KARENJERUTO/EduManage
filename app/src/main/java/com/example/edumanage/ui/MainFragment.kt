package com.example.edumanage.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.edumanage.R
import com.example.edumanage.databinding.ActivityMainBinding
import com.example.edumanage.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var bind: FragmentMainBinding

    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
       bind = FragmentMainBinding.inflate(inflater, container, false)



        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        bind.bottomNavigation.setupWithNavController(navController)
        return bind.root
    }

}