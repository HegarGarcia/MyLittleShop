package com.hegargarcia.mylittleshop.authentication

import android.content.Context
import android.content.SharedPreferences
import com.hegargarcia.mylittleshop.dao.UserDao
import com.hegargarcia.mylittleshop.database.AppDatabase
import com.hegargarcia.mylittleshop.entity.User

class Auth(context: Context) {

    private var preferences: SharedPreferences? = null
    private var db: AppDatabase? = null
    private var userDao: UserDao? = null

    init {
        db = AppDatabase.getDatabase(context)
        userDao = db?.user()
        preferences = context.getSharedPreferences(
            "auth.data",
            Context.MODE_PRIVATE
        )
    }

    private val getCurrentUserId: Int
        get() = preferences?.getInt("user.id", -1)!!

    val isUserLoggedIn: Boolean
        get() = getCurrentUserId != -1

    val getCurrentUser: User
        get() = userDao?.getById(getCurrentUserId)!!

    fun logIn(email: String, password: String, remember: Boolean): User? {
        val user = userDao?.login(email, password)
        if (remember) {
            preferences?.edit()?.apply {
                putInt("user.id", user?.id!!)
                apply()
            }
        }
        return user
    }

    fun signup(user: User, remember: Boolean): Int {
        return userDao?.insert(user)!!
    }

    fun signOut() {
        preferences?.edit()?.apply {
            remove("user.id")
            apply()
        }
    }
}