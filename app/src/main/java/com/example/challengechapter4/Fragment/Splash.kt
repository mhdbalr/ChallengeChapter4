package com.example.challengechapter4.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.challengechapter4.R
import com.example.challengechapter4.databinding.FragmentSplashBinding


class Splash : Fragment() {

    lateinit var binding: FragmentSplashBinding
    lateinit var sharedSplash: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        Handler(Looper.myLooper()!!).postDelayed({

            sharedSplash =
                requireContext().getSharedPreferences("dataregistrasi", Context.MODE_PRIVATE)
            if (sharedSplash.getString("nama", "").equals("")) {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }else if (sharedSplash.getString("nama", "")!!.isNotEmpty()) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }
        }, 3000)
        return binding.root
    }


}