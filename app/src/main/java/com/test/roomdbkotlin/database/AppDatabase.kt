package com.test.roomdbkotlin.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.test.roomdbkotlin.dao.StudentDao
import com.test.roomdbkotlin.entity.StudentEntity

/**
 * Defining the database table class and database version.
 * We can mention here multiple entities.
 */
@Database(entities = arrayOf(StudentEntity::class), version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun studentDao():StudentDao

    //Java static block equivalent in kotlin

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "student-list.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}