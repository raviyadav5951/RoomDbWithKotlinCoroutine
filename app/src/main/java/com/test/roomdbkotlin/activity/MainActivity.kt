package com.test.roomdbkotlin.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.test.roomdbkotlin.R
import com.test.roomdbkotlin.database.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
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
            totalRecords = data?.size as Int
            Log.e(TAG, "size==$totalRecords")
        }

    }

    /**
     * Function to calculate the total records in the students table
     * Using Async and await
     */
    private suspend fun fetchRecordsUsingAsync(): Int {
        val result = GlobalScope.async(Dispatchers.IO) {
            val data = dbInstance?.studentDao()?.getAllStudentRecords()
            totalRecords = data?.size as Int
//            Log.e(TAG,"size==$totalRecords")
        }
        result.await()
        return totalRecords
    }

    private suspend fun fetchRecordsUsingWithContext(): Int {
        val result = withContext(Dispatchers.IO)
        {
            val data = dbInstance?.studentDao()?.getAllStudentRecords()
            totalRecords = data?.size as Int
        }
        return totalRecords
    }

    override fun onResume() {
        super.onResume()

        GlobalScope.launch(Dispatchers.Main) {
            //using async
            val students: Int = fetchRecordsUsingAsync() // fetch on IO thread

            //using withContext
            //val students: Int = fetchRecordsUsingWithContext() // fetch on IO thread
            displayRecordsCount(students) // back on UI thread
        }
    }

    private fun displayRecordsCount(students: Int) {

        tvTotalRecords.text = "Total records in database=$students"
    }
}

