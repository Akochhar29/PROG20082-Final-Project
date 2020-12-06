package com.example.project_vanrooyen_kochhar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login_screen.*
import java.net.URL

class LoginScreen : AppCompatActivity() {
    var username = ""
    var password = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

    }

    fun login(view: View) {
        username = username_et.text.toString();
        password = pwd_et.text.toString()

        val apiResponse = URL("http://mohameom.dev.fast.sheridanc.on.ca/login/verify.php?name=" + username + "&password=" + password).readText()
        if (username_et.text.toString() == getString(R.string.username)) {
            if (pwd_et.text.toString() == getString(R.string.password)) {

                val intent = Intent(this, MainScreen::class.java)
                intent.putExtra("username", username_et.text.toString())
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