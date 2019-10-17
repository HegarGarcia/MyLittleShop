package com.hegargarcia.mylittleshop

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteCursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

abstract class Model<M>(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {
        private const val DATABASE_NAME = "SHOP"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(tableCreationStatement)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    abstract val tableName: String
    abstract val tableCreationStatement: String

    abstract fun getContentValuesForClient(info: M): ContentValues
    abstract fun mapCursorToObject(cursor: Cursor): M

    fun getById(id: Int): M {
        return this.readableDatabase.query(
            tableName,
            null,
            "${BaseColumns._ID} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        ).let {
            it.moveToNext()
            mapCursorToObject(it)
        }
    }

    fun insert(info: M): Int {
        val clientValues = getContentValuesForClient(info)
        return this.writableDatabase.let {
            val id = it.insert(tableName, null, clientValues)
            it.close()
            id.toInt()
        }
    }

    fun deleteOneById(id: Int): Int {
        return this.writableDatabase.delete(tableName, "${BaseColumns._ID} = ?", arrayOf(id.toString()))
    }

    fun update(id: Int, info: M): Int {
        val clientValues = getContentValuesForClient(info)
        return this.writableDatabase.update(
            tableName,
            clientValues,
            "${BaseColumns._ID} = ?",
            arrayOf(id.toString())
        )
    }
}