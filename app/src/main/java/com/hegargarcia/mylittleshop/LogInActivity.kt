package com.hegargarcia.mylittleshop

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hegargarcia.mylittleshop.authentication.Auth
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {

    private var auth: Auth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        auth = Auth(this)

        if (auth?.isUserLoggedIn!!) {
            auth?.getCurrentUser.also {
                userNamePrompt.setText(it?.username)
                passwordPrompt.setText((it?.password))
                rememberCheckBox.isChecked = true
            }
        }

        logInButton.setOnClickListener {
            logIn()
        }

        signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun logIn() {
        val user = auth?.logIn(
            username = userNamePrompt.text.toString(),
            password = passwordPrompt.text.toString(),
            remember = rememberCheckBox.isChecked
        )

        if (user == null) {
            Toast.makeText(this, R.string.user_does_not_exists, Toast.LENGTH_LONG).show()
        } else {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }
    }
}
