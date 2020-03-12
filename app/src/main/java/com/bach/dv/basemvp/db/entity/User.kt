package com.bach.dv.basemvp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bach.dv.basemvp.db.DBConstant
import com.google.gson.annotations.SerializedName

@Entity(tableName = DBConstant.USERS_TABLE_NAME)
class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DBConstant.USER_ID)
    private var id: Int = 0

    @SerializedName("first_name")
    @ColumnInfo(name = DBConstant.USER_FIRST_NAME)
    private var firstName: String? = null
}