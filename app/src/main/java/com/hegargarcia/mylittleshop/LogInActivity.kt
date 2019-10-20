package com.hegargarcia.mylittleshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        signUpButton.setOnClickListener{
            val intento = Intent(this,SignUpActivity::class.java)
            startActivity(intento)
        }
    }
}
