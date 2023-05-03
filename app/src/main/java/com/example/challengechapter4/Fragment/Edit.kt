package com.example.challengechapter4.Fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.challengechap4.dbroom.NoteData
import com.example.challengechap4.dbroom.NoteDataBase
import com.example.challengechapter4.R
import com.example.challengechapter4.databinding.FragmentEditBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class Edit : Fragment() {
    lateinit var binding: FragmentEditBinding
    var dbNote: NoteDataBase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbNote = NoteDataBase.getInstance(requireContext())

        // var getDataNote = activity?.intent?.getSerializableExtra("editData") as DataNote
        var getNoteData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("editData", NoteData::class.java)
        } else {
            arguments?.getParcelable("editData")
        }

        if (getNoteData != null) {
            binding.etJudul.setText(getNoteData.title)
        }
        if (getNoteData != null) {
            binding.etIsiCatatan.setText(getNoteData.content)
        }
        if (getNoteData != null) {
            binding.etId.setText(getNoteData.id.toString())
        }

        binding.btnEdit.setOnClickListener {
            editNote()
        }
    }

    fun editNote() {
        var idNote = binding.etId.text.toString().toInt()
        var title = binding.etJudul.text.toString()
        var note = binding.etIsiCatatan.text.toString()


        GlobalScope.async {
            var edit = dbNote?.noteDao()?.updateNote(NoteData(idNote, title, note))

        }
        Toast.makeText(requireContext(), "Data Berhasil di Edit", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_editFragment_to_homeFragment)
    }

}