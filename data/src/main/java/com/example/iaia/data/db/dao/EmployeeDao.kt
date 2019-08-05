package com.example.iaia.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.iaia.data.model.Employee

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<Employee>)

    @Query("SELECT * FROM Employee")
    fun getAll(): List<Employee>

    @Query("SELECT * FROM Employee WHERE id = :id")
    fun getById(id: Int): Employee?
}