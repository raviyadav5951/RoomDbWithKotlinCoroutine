package com.test.roomdbkotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.roomdbkotlin.R
import com.test.roomdbkotlin.adapter.ViewStudentsAdapter

class ViewStudentsActivity:AppCompatActivity() {

    var rvRecords: RecyclerView? = null
    lateinit var viewStudentsAdapter:ViewStudentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_records)
        rvRecords?.layoutManager= LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        viewStudentsAdapter=ViewStudentsAdapter(layoutInflater,this)
    }
}