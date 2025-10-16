
package com.example.inventoryapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CSE F Users")
data class User(
    @PrimaryKey(autoGenerate = true) val uid:Int,
    val userName:String,
    val userPhone:Int,
)

