package com.example.challengechapter4.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.challengechapter4.R
import com.example.challengechapter4.databinding.FragmentLoginBinding

class Login : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var dataPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataPref = requireContext().getSharedPreferences("dataregistrasi", Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            var getUsername = dataPref.getString("username", "")
            var getPassword = dataPref.getString("password", "")

            var username = binding.etUsername.text.toString()
            var password = binding.etPassword.text.toString()

            if (username == getUsername && password.equals(getPassword)) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                Toast.makeText(context, "Username atau password anda salah !", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.tvSignUp.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

}
