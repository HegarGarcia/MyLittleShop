package com.hegargarcia.mylittleshop.database

import android.content.Context
import androidx.room.*
import com.hegargarcia.mylittleshop.dao.*
import com.hegargarcia.mylittleshop.entity.*

@Database(entities = [User::class, Client::class, Product::class, Sell::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun user(): UserDao
    abstract fun client(): ClientDao
    abstract fun product(): ProductDao
    abstract  fun sell(): SellDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "MyLittleStore"
                    ).build()
                }
            }

            return INSTANCE
        }

        fun close() {
            INSTANCE = null
        }
    }
}