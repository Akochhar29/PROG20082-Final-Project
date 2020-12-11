package com.example.project_vanrooyen_kochhar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_edit_record.*


class EditRecord : AppCompatActivity() {

    var vm  : MyViewModel? = null
    var id = 0
    var score = 0
    var comment = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_record)

        vm = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MyViewModel::class.java)

        id = intent.getIntExtra("pId", 0)
        score = intent.getIntExtra("pScore", 0)
        comment = intent.getStringExtra("pComment").toString()

        showId_tv.text = getString(R.string.student_id)+ " " + id.toString()
        editScore_et.setText(score.toString())
        editComment_et.setText(comment)

    }


    fun updateRecord(view: View) {

        val db = MyDatabase.getDatabase(this)
        if (db != null) {
            score = editScore_et.text.toString().toInt()
            comment = editComment_et.text.toString()
            val student = Student(id, score, comment)
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