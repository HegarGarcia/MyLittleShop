package com.hegargarcia.mylittleshop

import ProductInfo
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns

class Product(context: Context) : Model<ProductInfo>(context) {

    override val tableName = "PRODUCT"

    override val tableCreationStatement =
        "CREATE TABLE $tableName (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "${ProductInfo.NAME} TEXT NOT NULL, " +
                "${ProductInfo.DESCRIPTION} TEXT NOT NULL, " +
                "${ProductInfo.AMOUNT} INTEGER NOT NULL" +
                "${ProductInfo.PRICE} REAL NOT NULL" +
                "${ProductInfo.COST} REAL NOT NULL" +
                "${ProductInfo.PHOTO_URL} TEXT NOT NULL" +
                ")"

    override fun getContentValuesForClient(info: ProductInfo): ContentValues {
        return ContentValues().apply {
            put(ProductInfo.NAME, info.name)
            put(ProductInfo.DESCRIPTION, info.description)
            put(ProductInfo.AMOUNT, info.amount)
            put(ProductInfo.PRICE, info.price)
            put(ProductInfo.COST, info.cost)
            put(ProductInfo.PHOTO_URL, info.photoUrl)
        }
    }

    override fun mapCursorToObject(cursor: Cursor): ProductInfo {
        return cursor.let {
            ProductInfo().apply {
                id = it.getInt(it.getColumnIndexOrThrow(BaseColumns._ID))
                name = it.getString(it.getColumnIndexOrThrow(ProductInfo.NAME))
                description = it.getString(it.getColumnIndexOrThrow(ProductInfo.DESCRIPTION))
                amount = it.getInt(it.getColumnIndexOrThrow(ProductInfo.AMOUNT))
                price = it.getFloat(it.getColumnIndexOrThrow(ProductInfo.PRICE))
                cost = it.getFloat(it.getColumnIndexOrThrow(ProductInfo.COST))
                photoUrl = it.getString(it.getColumnIndexOrThrow(ProductInfo.PHOTO_URL))
            }
        }
    }
}