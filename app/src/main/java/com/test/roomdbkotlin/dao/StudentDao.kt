package com.test.roomdbkotlin.dao

import android.arch.persistence.room.*
import com.test.roomdbkotlin.entity.StudentEntity

/**
 * This interface will contain the methods for following CRUD operations.
 * C=Create,R=Read,U=Update and D=Delete operation
 */
@Dao
interface StudentDao {

    //return type will be list we will fetch and display records in the list later.
    @Query("SELECT * FROM student_record")
    fun getAllStudentRecords():List<StudentEntity>


    //vvimp : query parameter name (stud_name)used with like should be same in function parameter
    @Query("SELECT * FROM student_record WHERE student_name LIKE :stud_name")
    fun findStudentByName(stud_name:String):StudentEntity

    @Insert
    fun insertStudent(vararg students:StudentEntity)

    @Update
    fun updateStudent(vararg students:StudentEntity)


    @Delete
    fun deleteStudent(student:StudentEntity)

}