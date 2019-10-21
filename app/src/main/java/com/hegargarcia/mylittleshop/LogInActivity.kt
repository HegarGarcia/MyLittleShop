package com.hegargarcia.mylittleshop

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hegargarcia.mylittleshop.authentication.Auth
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {

    private var auth: Auth? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (auth?.isUserLoggedIn!!) {
            auth?.getCurrentUser?.also {
                userNamePrompt.setText(it.username)
                passwordPrompt.setText((it.password))
                rememberCheckBox.isChecked = true
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        auth = Auth(this)

        logInButton.setOnClickListener {
            logIn()
        }

        signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        if (auth?.isUserLoggedIn!!) {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }
    }

    private fun logIn() {
        progressBar.visibility = View.VISIBLE

        val user = auth?.logIn(
            username = userNamePrompt.text.toString(),
            password = passwordPrompt.text.toString(),
            remember = rememberCheckBox.isChecked
        )

        userNamePrompt.clearFocus()
        passwordPrompt.clearFocus()
        rememberCheckBox.isChecked = false

        if (user == null) {
            Toast.makeText(this, R.string.user_does_not_exists, Toast.LENGTH_LONG).show()
        } else {
            userNamePrompt.text.clear()
            passwordPrompt.text.clear()

            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }

        progressBar.visibility = View.GONE
    }
}
