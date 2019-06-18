package com.test.roomdbkotlin.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.test.roomdbkotlin.R
import com.test.roomdbkotlin.database.AppDatabase
import com.test.roomdbkotlin.entity.StudentEntity
import kotlinx.android.synthetic.main.activity_create_student.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Create Student Activity to create new student record in database.
 */
class CreateStudentActivity : AppCompatActivity() {

    private var dbInstance: AppDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_student)

        dbInstance = AppDatabase.getInstance(this)

        btnSave.setOnClickListener {
            if (etName.text.toString().trim().isNotBlank() &&
                etAddress.text.toString().trim().isNotBlank()) {

                val studentRecord = StudentEntity(
                    0, etName.text.toString().trim(),
                    etAddress.text.toString().trim())

                //separate thread to perform operation on database
                GlobalScope.launch (Dispatchers.IO){
                    dbInstance?.studentDao()?.insertStudent(studentRecord)
                    finish()
                }
            }
            else{
                Toast.makeText(this, "Please enter required values",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}