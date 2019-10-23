package com.hegargarcia.mylittleshop.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hegargarcia.mylittleshop.converter.DateTypeConverter
import com.hegargarcia.mylittleshop.dao.ClientDao
import com.hegargarcia.mylittleshop.dao.ProductDao
import com.hegargarcia.mylittleshop.dao.SellDao
import com.hegargarcia.mylittleshop.dao.UserDao
import com.hegargarcia.mylittleshop.entity.Client
import com.hegargarcia.mylittleshop.entity.Product
import com.hegargarcia.mylittleshop.entity.Sell
import com.hegargarcia.mylittleshop.entity.User

@Database(
    entities = [User::class, Client::class, Product::class, Sell::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun user(): UserDao
    abstract fun client(): ClientDao
    abstract fun product(): ProductDao
    abstract fun sell(): SellDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "MyLittleStore"
                    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                }
            }

            return INSTANCE
        }

    }
}