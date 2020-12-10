package com.example.project_vanrooyen_kochhar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login_screen.*
import kotlinx.android.synthetic.main.activity_main_screen.*

class MainScreen : AppCompatActivity() {

    var choice = Option.NONE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        selectOption_tv.text = getString(R.string.greet) + " " + intent.getStringExtra("username") + "\n" + getString(R.string.option)
    }

    fun radioChoice(view: View) {
        when (view){
            enterNew_rb -> choice = Option.NEWRECORD
            displayPrevious_rb -> choice = Option.PREVRECORD
            logout_rb -> choice = Option.LOGOUT
        }
    }

    fun onSubmit(view: View) {
        if (choice != Option.NONE) {
            when (choice) {
                Option.NEWRECORD -> {
                    intent = Intent(this, NewRecord::class.java)
                    startActivity(intent)
                    choice = Option.NONE
                    enterNew_rb.isChecked = false
                }
                Option.PREVRECORD -> {
                    intent = Intent(this, AllRecords::class.java)
                    startActivity(intent)
                    choice = Option.NONE
                    displayPrevious_rb.isChecked = false
                }
                Option.LOGOUT -> finish() //Logout
            }
        }
    }

    enum class Option {
        NONE,
        NEWRECORD,
        PREVRECORD,
        LOGOUT
    }
}