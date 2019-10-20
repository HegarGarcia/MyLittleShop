package com.hegargarcia.mylittleshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hegargarcia.mylittleshop.authentication.Auth
import com.hegargarcia.mylittleshop.dao.UserDao
import com.hegargarcia.mylittleshop.database.AppDatabase
import com.hegargarcia.mylittleshop.entity.User
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private var auth: Auth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = Auth(this)

        signUpButton.setOnClickListener{
            addUser()
        }

        cancelButton.setOnClickListener{
            finish()
        }
    }

    private fun addUser(){
        val user = User(
            username = userPrompt.text.toString(),
            password = passwordPrompt.text.toString(),
            email = emailPrompt.text.toString(),
            storeName = "My Little Shop xd"
        )

        auth?.signUp(user)
        finish()
    }
}
