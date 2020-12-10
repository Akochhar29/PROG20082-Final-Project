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
import java.io.BufferedReader
import java.io.InputStream
import java.lang.Exception
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class LoginScreen : AppCompatActivity() {
    var username = ""
    var password = ""
    var sharePreferences = "sharedPrefsFile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        names_tv.text = getString(R.string.Akshay) + "\n" + getString(R.string.Luke)

        val sharedPrefs = getSharedPreferences(sharePreferences, Context.MODE_PRIVATE)
        val autoLog = sharedPrefs.getString(username, "")
        if (autoLog != null) {
            if (autoLog.isNotBlank()) {
                val intent = Intent(this, MainScreen::class.java)
                intent.putExtra("username", autoLog)
                startActivity(intent)
            }
        }

    }

    fun login(view: View) {
        var loginResponse  = ""
        username = username_et.text.toString();
        password = pwd_et.text.toString()

        var url = URL("http://mohameom.dev.fast.sheridanc.on.ca/login/verify.php?name=" + username + "&password=" + password)
        loginResponse = loadData(url)


        if (loginResponse == "valid") {

            val intent = Intent(this, MainScreen::class.java)
            intent.putExtra("username", username_et.text.toString())
            startActivity(intent)

            if (keeplogged_cb.isChecked) {
                val sharedPrefs = getSharedPreferences(sharePreferences, Context.MODE_PRIVATE)
                val editor = sharedPrefs.edit()

                editor.putString(username, password);
                editor.commit()
            }

        } else if (loginResponse == "invalid") {
            Toast.makeText(
                applicationContext,
                "Wrong password. Please try again.",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    fun loadData(url : URL): String {
        var ins : InputStream? = null
        var res = ""
        var valid = ""
        try {
            val conn = url.openConnection() as HttpsURLConnection
            conn.requestMethod = "GET"

            ins = conn.inputStream

            res = ins.bufferedReader().use(BufferedReader::readText)
            var json = JSONObject(res)
            valid = json.optString("login")
            
        } catch (e : Exception){


        }

        return valid

    }
}
