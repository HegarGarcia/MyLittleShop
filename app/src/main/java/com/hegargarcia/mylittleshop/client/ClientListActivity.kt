package com.hegargarcia.mylittleshop.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hegargarcia.mylittleshop.R
import android.content.Intent
import kotlinx.android.synthetic.main.activity_client_list.*

class ClientListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_list)

        addClientButton.setOnClickListener{
            val intento = Intent(this,ClientFormActivity::class.java)
            startActivity(intento)
        }
    }
}
