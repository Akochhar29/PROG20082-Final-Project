package com.example.project_vanrooyen_kochhar

import android.telecom.Call.Details
import androidx.room.*


@Dao
interface StudentDao {

    @Insert
    fun insertAll(vararg students: Student)

    @Query("SELECT * From student")
    fun getAll() : List<Student>

    @Update
    fun update(student: Student)

    @Delete
    fun delete(student: Student)

}