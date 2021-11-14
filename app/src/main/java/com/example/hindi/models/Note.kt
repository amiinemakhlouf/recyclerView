package com.example.hindi.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import retrofit2.http.Body

@Entity(tableName = "note")
data class Note (
    val title:String?=null,
    val body:String?=null,
    val timeStamp:String?=null,
        )
{   @PrimaryKey(autoGenerate = true)
    var id:Int=0
}
