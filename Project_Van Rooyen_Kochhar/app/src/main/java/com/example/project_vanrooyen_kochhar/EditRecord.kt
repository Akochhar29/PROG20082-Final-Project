package com.example.project_vanrooyen_kochhar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider

class EditRecord : AppCompatActivity() {

    var vm  : MyViewModel? = null
    var id = 0
    var score = 0
    var comments = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_record)

        vm = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MyViewModel::class.java)
        val intent = Intent(this, MainScreen::class.java)
        //id = intent.getStringExtra()
        


    }


    fun updateRecord(view: View) {

        val db = MyDatabase.getDatabase(this)
        if (db != null) {
            val student = Student(id, score, comments)
            vm?.update(student)

            val intent = Intent(this, AllRecords::class.java)
            startActivity(intent)

        }
    }

    fun cancelEdit(view: View) {
        val intent = Intent(this, AllRecords::class.java)
        startActivity(intent)
    }
}