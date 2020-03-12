package com.bach.dv.basemvp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bach.dv.basemvp.db.dao.UserDao
import com.bach.dv.basemvp.db.entity.User

@Database(
    entities = [User::class],
    version = 13,
    exportSchema = false
)
abstract class LocalDB : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}