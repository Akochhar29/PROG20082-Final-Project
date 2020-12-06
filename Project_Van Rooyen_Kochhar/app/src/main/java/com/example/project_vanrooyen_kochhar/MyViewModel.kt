package com.example.project_vanrooyen_kochhar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MyViewModel(app : Application) : AndroidViewModel(app) {

    lateinit var students : ArrayList<Student>

    init{

        students =  ArrayList<Student>()

        getStudents()

    }

    fun getStudents() {

        val db = MyDatabase.getDatabase(getApplication())
        if( db != null) {
            val list = db.studentDao().getAll()

            students = list.toCollection(ArrayList<Student>())
        }

    }

    fun update( s :Student){
        val db = MyDatabase.getDatabase(getApplication())
        if( db != null) {
            db.studentDao().update(s)
            getStudents()

        }
    }

}