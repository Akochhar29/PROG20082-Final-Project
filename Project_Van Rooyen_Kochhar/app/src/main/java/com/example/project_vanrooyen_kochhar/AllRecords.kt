package com.example.project_vanrooyen_kochhar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_all_records.*

class AllRecords : AppCompatActivity() {
    var vm  : MyViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_records)
        vm = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MyViewModel::class.java)

        /*
        if (vm != null) {
            val layoutManager = LinearLayoutManager(this)
            vm!!.getStudents()
            val data: ArrayList<Student>? = vm?.students
            val adapter = data?.let { Adapter(it, this) }
            records_rv.layoutManager = layoutManager
            records_rv.adapter = adapter
        }valid
         */
    }

    fun returnToSender(view: View){
        finish()
    }

}