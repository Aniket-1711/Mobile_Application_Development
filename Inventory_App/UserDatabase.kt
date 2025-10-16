package com.example.inventoryapp

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase(){
    abstract fun userDao(): UserDao


    companion object{
        @Volatile
        var INSTANCE:UserDatabase?=null

        fun getInstance(context: Context): UserDatabase{
            return INSTANCE?:synchronized(this){
                val instace=Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "aniket_db"
                ).build()
                INSTANCE=instace
                instace
            }
        }
    }
}
