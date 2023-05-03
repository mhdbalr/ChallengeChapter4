package com.example.challengechap4.dbroom

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Entity
@Parcelize
data class NoteData(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var title : String,
    var content : String
) : Parcelable
