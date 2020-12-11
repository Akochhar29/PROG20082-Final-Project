package com.example.project_vanrooyen_kochhar

import android.content.Context
import androidx.room.*

@Database(entities = arrayOf(Student::class) , version = 1)
abstract class MyDatabase : RoomDatabase() {

    // Setting up the DB to use studentDao
    abstract fun  studentDao() : StudentDao
    companion object {
        private var instance: MyDatabase? = null
        // Retrieving the DB
        fun getDatabase(context: Context): MyDatabase? {
            if (instance == null) {
                synchronized(MyDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "MDB"
                    ).build()

                }

            }

            return instance
        }
    }
}