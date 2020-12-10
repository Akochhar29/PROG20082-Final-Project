package com.example.project_vanrooyen_kochhar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_record.*

class NewRecord : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_record)
        score_sb.max = 10;

        save_btn.text = getString(R.string.save)


    }

    fun saveStudent(view: View) {

        if (id_et.text.isNotBlank()) {
            val id = id_et.text.toString().toInt()
            val score = score_sb.progress
            val comments = comments_et.text.toString()

            val student  = Student(id, score, comments)
            Thread {
                val db = MyDatabase.getDatabase(this)
                if (db != null) {
                    db.studentDao().insertAll(student)

                    runOnUiThread {
                        id_et.text.clear()
                        score_sb.progress = 0
                        comments_et.text.clear()
                    }
                }
            }.start()
        } else {
            Toast.makeText(this, "Please provide a student Id", Toast.LENGTH_SHORT).show()
        }
    }

    fun retToMain(view: View) {
        finish()
    }
}