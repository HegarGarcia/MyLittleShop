package com.hegargarcia.mylittleshop

import ClientInfo
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns

class Client(context: Context) : Model<ClientInfo>(context) {

    override val tableName = "CLIENT"

    override val tableCreationStatement =
        "CREATE TABLE $tableName (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "${ClientInfo.NAME} TEXT NOT NULL, " +
                "${ClientInfo.EMAIL} TEXT NOT NULL, " +
                "${ClientInfo.ADDRESS} TEXT NOT NULL" +
                ")"

    override fun getContentValuesForClient(info: ClientInfo): ContentValues {
        return ContentValues().apply {
            put(ClientInfo.NAME, info.name)
            put(ClientInfo.EMAIL, info.email)
            put(ClientInfo.ADDRESS, info.address)
        }
    }

    override fun mapCursorToObject(cursor: Cursor): ClientInfo {
        return cursor.let {
            ClientInfo().apply {
                id = it.getInt(it.getColumnIndexOrThrow(BaseColumns._ID))
                name = it.getString(it.getColumnIndexOrThrow(ClientInfo.NAME))
                email = it.getString(it.getColumnIndexOrThrow(ClientInfo.EMAIL))
                address = it.getString(it.getColumnIndexOrThrow(ClientInfo.ADDRESS))
            }
        }
    }

}