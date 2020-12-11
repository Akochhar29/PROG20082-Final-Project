package com.example.project_vanrooyen_kochhar

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MyViewModel(app : Application) : AndroidViewModel(app) {
    var validity = MutableLiveData<String>()
    lateinit var students: ArrayList<Student>

    init {

        students = ArrayList<Student>()

        getStudents()

    }

    fun getStudents() {

        val db = MyDatabase.getDatabase(getApplication())
        if (db != null) {
            val list = db.studentDao().getAll()

            students = list.toCollection(ArrayList<Student>())
        }

    }

    fun update(s: Student) {
        val db = MyDatabase.getDatabase(getApplication())
        if (db != null) {
            db.studentDao().update(s)
            getStudents()

        }
    }
    fun delete(s: Student){
        val db = MyDatabase.getDatabase(getApplication())
        if (db != null) {
            db.studentDao().delete(s)
            getStudents()

        }
    }

    suspend fun makeRequest(url: URL) : String {
        var ins: InputStream? = null
        var res = ""

        try {
            Log.d("ss", "ss")
            val conn = url.openConnection() as HttpsURLConnection
            conn.requestMethod = "GET"

            ins = conn.inputStream

            res = ins.bufferedReader().use(BufferedReader::readText)

        } catch (e: Exception) {

            Log.d("TAG", e.toString())
        }
        return res
    }

    fun getValidity(url: URL) {


        val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
            var res = makeRequest(url)

            withContext(Dispatchers.Main) {
                try {
                    var valid = ""
                    var json = JSONObject(res)
                    valid = json.optString("login")
                    this@MyViewModel.validity.value = valid
                    Log.d("nn", "nn")
                } catch (e: Exception) {
                    Log.d("TAG", e.toString())

                }
            }
        }

    }
}