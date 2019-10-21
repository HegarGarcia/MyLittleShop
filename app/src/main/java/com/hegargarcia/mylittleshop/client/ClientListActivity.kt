package com.hegargarcia.mylittleshop.client

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.hegargarcia.mylittleshop.R
import com.hegargarcia.mylittleshop.database.AppDatabase
import com.hegargarcia.mylittleshop.entity.Client
import kotlinx.android.synthetic.main.activity_client_list.*

class ClientListActivity : AppCompatActivity() {

    companion object {
        private const val FORM_ACTIVITY_CODE = 10
    }

    private var clientList: List<Client>? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        showClientsOnListView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_list)
        showClientsOnListView()

        toolbar_client_list.setNavigationOnClickListener {
            finish()
        }

        addButton.setOnClickListener {
            val intent = Intent(this, ClientFormActivity::class.java)
            startActivityForResult(intent, FORM_ACTIVITY_CODE)
        }

        clientListView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, ClientFormActivity::class.java)
            val selectedClient = clientList?.get(position)
            intent.putExtra("client.id", selectedClient?.id)
            startActivityForResult(intent, FORM_ACTIVITY_CODE)
        }
    }

    private fun showClientsOnListView() {
        clientList = AppDatabase.getDatabase(this).let {
            val clientDao = it?.client()
            clientDao?.getAll()
        }

        val clientsAdapter =
            ArrayAdapter<Client>(this, android.R.layout.simple_list_item_1, clientList!!)
        clientListView.adapter = clientsAdapter
    }
}
