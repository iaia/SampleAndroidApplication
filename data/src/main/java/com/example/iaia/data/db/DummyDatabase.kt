package com.example.iaia.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.iaia.data.db.dao.EmployeeDao
import com.example.iaia.data.model.Employee

@Database(
    entities = [
        Employee::class
    ], version = 1
)
abstract class DummyDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao

    companion object {
        @Volatile
        private var INSTANCE: DummyDatabase? = null

        fun getDatabase(context: Context): DummyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DummyDatabase::class.java,
                    "dummy"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
