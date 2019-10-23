package com.hegargarcia.mylittleshop

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hegargarcia.mylittleshop.authentication.Auth
import com.hegargarcia.mylittleshop.dao.UserDao
import com.hegargarcia.mylittleshop.database.AppDatabase
import com.hegargarcia.mylittleshop.entity.User
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private var userDao: UserDao? = null
    private var auth: Auth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        userDao = AppDatabase.getDatabase(this)?.user()
        auth = Auth(this)

//        userDao?.getById(1)?.run {
//            storeNamePrompt.setText(storeName)
//            storeNamePrompt.isEnabled = false
//        }

        signUpButton.setOnClickListener {
            addUser()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun addUser() {
        val user = User(
            username = userPrompt.text.toString(),
            password = passwordPrompt.text.toString(),
            email = emailPrompt.text.toString(),
            storeName = storeNamePrompt.text.toString()
        )

        Toast.makeText(this, "Te has registrado con exito", Toast.LENGTH_LONG).show()

        auth?.signUp(user)
        finish()
    }
}
