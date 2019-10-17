package com.hegargarcia.mylittleshop

import UserInfo
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns

class User(context: Context) : Model<UserInfo>(context) {

    override val tableName = "USER"

    override val tableCreationStatement =
        "CREATE TABLE $tableName (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "${UserInfo.EMAIL} TEXT NOT NULL, " +
                "${UserInfo.PASSWORD} TEXT NOT NULL, " +
                "${UserInfo.USERNAME} TEXT NOT NULL, " +
                "${UserInfo.STORE_NAME} TEXT NOT NULL, " +
                ")"

    override fun getContentValuesForClient(info: UserInfo): ContentValues {
        return ContentValues().apply {
            put(UserInfo.USERNAME, info.username)
            put(UserInfo.EMAIL, info.email)
            put(UserInfo.PASSWORD, info.password)
            put(UserInfo.STORE_NAME, info.storeName)
        }
    }

    override fun mapCursorToObject(cursor: Cursor): UserInfo {
        return cursor.let {
            UserInfo().apply {
                id = it.getInt(it.getColumnIndexOrThrow(BaseColumns._ID))
                username = it.getString(it.getColumnIndexOrThrow(UserInfo.USERNAME))
                email = it.getString(it.getColumnIndexOrThrow(UserInfo.EMAIL))
                password = it.getString(it.getColumnIndexOrThrow(UserInfo.PASSWORD))
                storeName = it.getString(it.getColumnIndexOrThrow(UserInfo.STORE_NAME))
            }
        }
    }

}