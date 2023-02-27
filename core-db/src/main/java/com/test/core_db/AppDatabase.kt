package com.test.core_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.core_db.dao.UserDao
import com.test.core_db.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userInfoDao():UserDao
}