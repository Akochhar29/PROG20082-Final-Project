package com.example.project_vanrooyen_kochhar

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main_screen.*

class MainScreen : AppCompatActivity() {

    var sharePreferences = "sharedPrefsFile"
    var choice = Option.NONE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        selectOption_tv.text = getString(R.string.greet) + " " + intent.getStringExtra("username") + "\n" + getString(R.string.option)
    }

    override fun onResume() {
        super.onResume()
        radioGroup.clearCheck()
        choice = Option.NONE

    }

    fun radioChoice(view: View) {
        choice = Option.NONE
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
                }
                Option.PREVRECORD -> {
                    intent = Intent(this, AllRecords::class.java)
                    startActivity(intent)
                }
                Option.LOGOUT -> {
                    val sharedPrefs = getSharedPreferences(sharePreferences, Context.MODE_PRIVATE)
                    val editor = sharedPrefs.edit()
                    editor.clear()
                    editor.commit()
                    finish()
                } //Logout
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