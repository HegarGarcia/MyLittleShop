package com.hegargarcia.mylittleshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hegargarcia.mylittleshop.dao.UserDao
import com.hegargarcia.mylittleshop.database.AppDatabase
import com.hegargarcia.mylittleshop.entity.User
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private var db: AppDatabase? = null
    private var userDao: UserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        db = AppDatabase?.getDatabase(this)
        userDao = db?.user()

        signUpButton.setOnClickListener{
            addUser()
        }
    }

    private fun addUser(){
        val user = User(
            username = userPrompt.text.toString(),
            password = passwordPrompt.text.toString(),
            email = emailPrompt.text.toString(),
            storeName = "My Little Shop xd"
        )

        userDao?.insert(user)
    }
}
