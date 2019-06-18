package com.test.roomdbkotlin.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.test.roomdbkotlin.R
import com.test.roomdbkotlin.database.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var dbInstance: AppDatabase? = null
    private var totalRecords: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get instance of database
        dbInstance = AppDatabase.getInstance(this)

        btnAddStudent.setOnClickListener {
            val createStudent = Intent(this, CreateStudentActivity::class.java)
            startActivity(createStudent)
        }

        btnViewStudents.setOnClickListener {
            val createStudent = Intent(this, ViewStudentsActivity::class.java)
            startActivity(createStudent)
        }

    }

    /**
     * Function to calculate the total records in the students table
     */

    private fun fetchTotalRecords() {
        //separate thread to perform operation on database
        GlobalScope.launch(Dispatchers.IO) {
            val data = dbInstance?.studentDao()?.getAllStudentRecords()
            totalRecords=data?.size as Int
        }

    }

    override fun onResume() {
        super.onResume()
        fetchTotalRecords()
        tvTotalRecords.text = "Total records in database=$totalRecords"
    }
}

