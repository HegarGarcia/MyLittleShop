package com.hegargarcia.mylittleshop.client

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hegargarcia.mylittleshop.R
import com.hegargarcia.mylittleshop.authentication.Auth
import com.hegargarcia.mylittleshop.dao.ClientDao
import com.hegargarcia.mylittleshop.database.AppDatabase
import com.hegargarcia.mylittleshop.entity.Client
import kotlinx.android.synthetic.main.activity_client_form.*

class ClientFormActivity : AppCompatActivity() {

    private var clientDao: ClientDao? = null
    private var client: Client? = null
    private var storeName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_form)

        AppDatabase.getDatabase(this).let {
            clientDao = it?.client()
            storeName = Auth(this).getCurrentUser?.storeName
        }

        val clientId = intent.getIntExtra("client.id", -1)

        if (clientId != -1) {
            client = clientDao?.getById(clientId, storeName!!)
            namePrompt.setText(client?.name)
            addressPrompt.setText(client?.address)
            emailPrompt.setText(client?.email)
            phonePrompt.setText(client?.phone)
            deleteButton.visibility = View.VISIBLE
        } else {
            deleteButton.visibility = View.GONE
        }

        saveClientButton.setOnClickListener {
            addClient()
        }

        toolbar_client_form.setNavigationOnClickListener {
            finish()
        }

        deleteButton.setOnClickListener {
            deleteClient()
        }
    }

    private fun addClient() {
        if (client?.id != null) {
            client.apply {
                this?.name = namePrompt.text.toString()
                this?.address = addressPrompt.text.toString()
                this?.email = emailPrompt.text.toString()
                this?.phone = phonePrompt.text.toString()
            }

            clientDao?.update(client!!)
        } else {
            client = Client(
                name = namePrompt.text.toString(),
                address = addressPrompt.text.toString(),
                email = emailPrompt.text.toString(),
                phone = phonePrompt.text.toString(),
                storeName = storeName!!
            )

            clientDao?.insert(client!!)
        }

        if (client != null) finish()
    }

    private fun deleteClient() {
        clientDao?.delete(client!!)
        finish()
    }
}
