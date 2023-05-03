package com.example.challengechapter4

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.challengechap4.dbroom.NoteData
import com.example.challengechap4.dbroom.NoteDataBase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteViewModel(app : Application): AndroidViewModel(app) {

    lateinit var allNote : MutableLiveData<List<NoteData>>

    init{
        allNote = MutableLiveData()
        getAllNote()
    }
    fun getAllNoteObservers(): MutableLiveData<List<NoteData>> {
        return allNote
    }

    fun getAllNote() {
        GlobalScope.launch {
            val userDao = NoteDataBase.getInstance(getApplication())!!.noteDao()
            val listnote = userDao.getDataNote()
            allNote.postValue(listnote)
        }
    }
}
