package com.example.challengechap4.dbroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteData::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {

    abstract fun noteDao() : NoteDao

    companion object{
        private var INSTANCE : NoteDataBase? = null

        fun getInstance(context: Context):NoteDataBase? {
            if (INSTANCE == null){
                synchronized(NoteDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NoteDataBase::class.java,"Note.db").build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}