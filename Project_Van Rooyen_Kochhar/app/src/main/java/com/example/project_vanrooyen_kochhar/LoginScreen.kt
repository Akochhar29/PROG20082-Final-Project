package com.example.project_vanrooyen_kochhar

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_login_screen.*
import java.net.URL

class LoginScreen : AppCompatActivity() {
    var username = ""
    var password = ""
    var sharePreferences = "sharedPrefsFile"
    lateinit var vm : MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        vm = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(MyViewModel::class.java)
        names_tv.text = getString(R.string.Akshay) + "\n" + getString(R.string.Luke)

        val sharedPrefs = getSharedPreferences(sharePreferences, Context.MODE_PRIVATE)
        val autoLog = sharedPrefs.getString("username", "")
            if (autoLog == "") {

            } else {
                val intent = Intent(this, MainScreen::class.java)
                intent.putExtra("username", autoLog)
                startActivity(intent)
            }

    }

    fun login(view: View) {

        username = username_et.text.toString();
        password = pwd_et.text.toString()

        var url = URL("https://mohameom.dev.fast.sheridanc.on.ca/login/verify.php?name=" + username + "&password=" + password)
        var loginResponse = vm.getValidity(url)


        if (loginResponse.value == "valid") {

            val intent = Intent(this, MainScreen::class.java)
            intent.putExtra("username", username_et.text.toString())
            startActivity(intent)

            if (keeplogged_cb.isChecked) {
                val sharedPrefs = getSharedPreferences(sharePreferences, Context.MODE_PRIVATE)
                val editor = sharedPrefs.edit()

                editor.putString("username", username)
                editor.commit()
            }

        } else if (loginResponse.value == "invalid") {
            Toast.makeText(
                applicationContext,
                "Wrong username or password. Please try again.",
                Toast.LENGTH_SHORT
            ).show()

        }
    }


}
