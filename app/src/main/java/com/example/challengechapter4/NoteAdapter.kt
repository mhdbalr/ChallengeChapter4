package com.example.challengechapter4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.challengechap4.dbroom.NoteData
import com.example.challengechap4.dbroom.NoteDataBase
import com.example.challengechapter4.Fragment.Home
import com.example.challengechapter4.databinding.ItemNoteBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class NoteAdapter(var listNote : List<NoteData>): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    var DBNote : NoteDataBase? = null

    class ViewHolder(var binding : ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        var view = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {

        holder.binding.note = listNote[position]

        holder.binding.btnEditNote.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("editData", listNote[position])
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_editFragment, bundle)
        }

        holder.binding.btnDeleteNote.setOnClickListener {
            DBNote = NoteDataBase.getInstance(it.context)

            GlobalScope.async {
                val del = DBNote?.noteDao()?.deleteNote(listNote[position])
                (holder.itemView.context as Home).activity?.runOnUiThread{
                    (holder.itemView.context as Home)
                }
            }
            Toast.makeText(it.context, "Data Berhasil di Hapus", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).navigate(R.id.homeFragment)
        }
    }

    override fun getItemCount(): Int {
        return  listNote.size
    }

    fun setNoteData(listNote: ArrayList<NoteData>)
    {
        this.listNote=listNote
    }
}
