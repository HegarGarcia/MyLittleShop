package com.hegargarcia.mylittleshop

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hegargarcia.mylittleshop.authentication.Auth
import com.hegargarcia.mylittleshop.database.AppDatabase
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_sell_form.*

class LogInActivity : AppCompatActivity() {

    private var auth: Auth? = null
    private var storeNameList: List<String>? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        storeNameList = AppDatabase.getDatabase(this).let { it?.user()?.getStoreNames() }

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
        storeNameList = AppDatabase.getDatabase(this).let { it?.user()?.getStoreNames() }

        val adapter =
            storeNameList?.let {
                ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                    it
                ).apply {
                    setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                }
            }

        clientSpinner.adapter = adapter

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

        val storeName = storeNameSpinner.selectedItemPosition.let { storeNameList!![it] }

        val user = auth?.logIn(
            username = userNamePrompt.text.toString(),
            password = passwordPrompt.text.toString(),
            storeName = storeName,
            remember = rememberCheckBox.isChecked
        )

        userNamePrompt.clearFocus()
        passwordPrompt.clearFocus()


        if (user == null) {
            Toast.makeText(this, R.string.user_does_not_exists, Toast.LENGTH_LONG).show()
        } else {
            userNamePrompt.text.clear()
            passwordPrompt.text.clear()
            rememberCheckBox.isChecked = false

            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }

        progressBar.visibility = View.GONE
    }
}
