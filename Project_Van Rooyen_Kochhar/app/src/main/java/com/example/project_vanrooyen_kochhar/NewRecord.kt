package com.example.project_vanrooyen_kochhar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_new_record.*

class NewRecord : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_record)
        score_sb.max = 10;


    }

    fun saveStudent(view: View) {
        val id = id_et.text.toString().toInt()
        val score = score_sb.progress
        val comments = comments_et.text.toString()
        val student  = Student(id, score, comments)
        Thread {
            val db = MyDatabase.getDatabase(this)
            if (db != null) {
                db?.studentDao().insertAll(student)

                runOnUiThread {
                    id_et.text.clear()
                    score_sb.progress = 0
                    comments_et.text.clear()
                }
            }
        }.start()
    }

    fun retToMain(view: View) {
        val intent = Intent(this, MainScreen::class.java)
        startActivity(intent)
    }
}