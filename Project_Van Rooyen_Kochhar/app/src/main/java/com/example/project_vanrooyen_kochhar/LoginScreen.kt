package com.example.project_vanrooyen_kochhar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login_screen.*

class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)


    }

    fun login(view: View) {
        if (username_et.text.toString() == "") {
            if (pwd_et.text.toString() == "") {

                val intent = Intent(this, MainScreen::class.java)
                intent.putExtra("username", username_et.toString())
                startActivity(intent)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Wrong password. Please try again.",
                    Toast.LENGTH_SHORT
                ).show()

            }
        } else {
            Toast.makeText(applicationContext,
                "Wrong username. Please try again.",
                Toast.LENGTH_SHORT).
            show()
        }

    }
}