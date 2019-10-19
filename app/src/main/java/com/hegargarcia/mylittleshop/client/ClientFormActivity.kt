package com.hegargarcia.mylittleshop.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hegargarcia.mylittleshop.R
import com.hegargarcia.mylittleshop.dao.ClientDao
import com.hegargarcia.mylittleshop.database.AppDatabase
import com.hegargarcia.mylittleshop.entity.Client
import kotlinx.android.synthetic.main.activity_client_form.*

class ClientFormActivity : AppCompatActivity() {

    private var db: AppDatabase? = null
    private var clientDao: ClientDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_form)

        db = AppDatabase?.getDatabase(this)
        clientDao = db?.client()

        saveClientButton.setOnClickListener {
            addClient()
        }

        cancelButton.setOnClickListener{
            finish()
        }
    }

    private fun addClient() {

        val client = Client(
            name = namePrompt.text.toString(),
            address = addressPrompt.text.toString(),
            email = emailPrompt.text.toString(),
            phone = phonePrompt.text.toString()
        )
        clientDao?.insert(client)
    }
}
