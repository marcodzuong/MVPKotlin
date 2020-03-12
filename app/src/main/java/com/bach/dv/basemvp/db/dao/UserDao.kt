package com.bach.dv.basemvp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bach.dv.basemvp.db.DBConstant
import com.bach.dv.basemvp.db.DBConstant.Companion.USERS_TABLE_NAME
import com.bach.dv.basemvp.db.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<User?>?)

    @Query("SELECT * FROM " + DBConstant.USERS_TABLE_NAME)
    fun getAll(): List<User>
}