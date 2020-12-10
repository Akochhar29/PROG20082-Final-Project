package com.example.project_vanrooyen_kochhar

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login_screen.*
import org.json.JSONObject
import org.xml.sax.Parser
import java.net.URL

        val loginResponse = "valid"
class LoginScreen : AppCompatActivity() {
    var username = ""
    var password = ""
    var sharePreferences = "sharedPrefsFile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        names_tv.text = getString(R.string.Akshay) + "\n" + getString(R.string.Luke)

        val sharedPrefs = getSharedPreferences(sharePreferences, Context.MODE_PRIVATE)
        val autoLog = sharedPrefs.getString(username,"")
        if (autoLog == ""){

        } else {
            val intent = Intent(this, MainScreen::class.java)
            intent.putExtra("username", username_et.text.toString())
            startActivity(intent)
        }
    }

    fun login(view: View) {
        username = username_et.text.toString();
        password = pwd_et.text.toString()

        //val urlResponse = JSONObject(URL("http://mohameom.dev.fast.sheridanc.on.ca/login/verify.php?name=" + username + "&password=" + password).readText())
        //val loginResponse = urlResponse.getJSONObject("login").toString()

         val loginResponse = "valid"

      if (loginResponse == "valid"){

           val intent = Intent(this, MainScreen::class.java)
           intent.putExtra("username", username_et.text.toString())
           startActivity(intent)

           if(keeplogged_cb.isChecked) {
               val sharedPrefs = getSharedPreferences(sharePreferences, Context.MODE_PRIVATE)
               val editor = sharedPrefs.edit()

               editor.putString(username, password);
               editor.commit()

           }

       } else if(loginResponse == "invalid"){
           Toast.makeText(
               applicationContext,
               "Wrong password. Please try again.",
               Toast.LENGTH_SHORT
           ).show()

       }

    }
}
