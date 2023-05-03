package com.example.challengechapter4.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengechap4.dbroom.NoteData
import com.example.challengechap4.dbroom.NoteDataBase
import com.example.challengechapter4.NoteAdapter
import com.example.challengechapter4.NoteViewModel
import com.example.challengechapter4.R
import com.example.challengechapter4.databinding.FragmentHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Home : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    var NoteDB : NoteDataBase? = null
    lateinit var adapterNote : NoteAdapter
    lateinit var noteViewModel : NoteViewModel
    lateinit var dataPref : SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        super.onViewCreated(view, savedInstanceState)
        dataPref= requireContext().getSharedPreferences("dataregistrasi", Context.MODE_PRIVATE)
        _binding?.tvWelcome?.text = "Welcome,  " + dataPref.getString("nama", "name")

        NoteDB = NoteDataBase.getInstance(requireContext())

        noteVm()

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        noteViewModel.getAllNoteObservers().observe(viewLifecycleOwner, Observer {
            adapterNote.setNoteData(it as ArrayList<NoteData>)
        })

        binding.btnAdd.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
        binding.btnLogout.setOnClickListener {
            val dataPref = dataPref.edit()
            dataPref.clear()
            dataPref.apply()
            Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_loginFragment)
        }

    }

    fun noteVm() {
        adapterNote = NoteAdapter(ArrayList())
        binding.rvNote.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvNote.adapter = adapterNote
    }

    override fun onStart() {
        super.onStart()

        GlobalScope.launch {
            var data = NoteDB?.noteDao()?.getDataNote()

            activity?.runOnUiThread {
                adapterNote = NoteAdapter(data!!)
                binding.rvNote.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.rvNote.adapter = adapterNote
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}