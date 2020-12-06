package com.example.project_vanrooyen_kochhar

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Student (
    @PrimaryKey var id : Int,
    var score : Int, var comment : String){


}