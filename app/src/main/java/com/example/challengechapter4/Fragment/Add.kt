package com.example.challengechapter4.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.challengechap4.dbroom.NoteData
import com.example.challengechap4.dbroom.NoteDataBase
import com.example.challengechapter4.R
import com.example.challengechapter4.databinding.FragmentAddBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class Add : Fragment() {
    lateinit var binding: FragmentAddBinding
    var dbNote : NoteDataBase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbNote =NoteDataBase.getInstance(requireContext())

        binding.btnSave.setOnClickListener{
            addNote()
        }
    }

    fun addNote(){
        GlobalScope.async {
            var title = binding.etJudul.text.toString()
            var note = binding.etIsiCatatan.text.toString()

            dbNote!!.noteDao().insertNote(NoteData(0,title, note))
        }
        findNavController().navigate(R.id.action_addFragment_to_homeFragment)
    }

}