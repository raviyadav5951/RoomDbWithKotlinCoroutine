package com.test.roomdbkotlin.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * VV IMP:
 * Entity is nothing but your database table.
 * In Entity: Minimum one field should be present and one field should be a primary key.
 */

@Entity(tableName = "student_record")
data class StudentEntity (

    //field one
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    //field two
    @ColumnInfo(name = "student_name")
    var student_name:String ,

    //field three
    @ColumnInfo(name="address")
    var address:String

)