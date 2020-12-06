package com.example.project_vanrooyen_kochhar

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {

    @Insert
    fun insertAll(vararg users: Student)

    @Query("SELECT * From student")
    fun getAll() : List<Student>

    @Update
    fun update(student : Student)
}