package com.example.project_vanrooyen_kochhar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_all_records.*

class AllRecords : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_records)

        val layoutManager = LinearLayoutManager(this)
        val data :ArrayList<Student> = getUsers()
        val adapter = Adapter(data, this)
        records_rv.layoutManager = layoutManager
        records_rv.adapter = adapter
    }

    private fun getUsers(): ArrayList<Student> {
        val db = MyDatabase.getDatabase(getApplication())
        var list = arrayListOf<Student>()
        if( db != null) {
            list = db.userDao().getAll().toCollection(ArrayList<Student>())
        }

        return list
    }
    

}