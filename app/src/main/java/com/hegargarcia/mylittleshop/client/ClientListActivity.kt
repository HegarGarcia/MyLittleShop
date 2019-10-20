package com.hegargarcia.mylittleshop.client

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.hegargarcia.mylittleshop.R
import com.hegargarcia.mylittleshop.dao.ClientDao
import com.hegargarcia.mylittleshop.database.AppDatabase
import com.hegargarcia.mylittleshop.entity.Client
import kotlinx.android.synthetic.main.activity_client_list.*

class ClientListActivity : AppCompatActivity() {

    private var clientDao: ClientDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_list)

        clientDao = AppDatabase.getDatabase(this).let {
            it?.client()
        }

        showClientsOnListView()
    }

    private fun showClientsOnListView() {
        val clientsAdapter =
            ArrayAdapter<Client>(this, android.R.layout.simple_list_item_1, clientDao?.getAll()!!)
        clientList.adapter = clientsAdapter
    }
}
