package com.test.roomdbkotlin.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.test.roomdbkotlin.R

class ViewStudentsActivity:AppCompatActivity() {

    var rvRecords: RecyclerView? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_records)
        rvRecords=findViewById(R.id.rvRecords)
    }
}