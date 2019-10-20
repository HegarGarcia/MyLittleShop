package com.hegargarcia.mylittleshop.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hegargarcia.mylittleshop.R
import com.hegargarcia.mylittleshop.dao.ClientDao
import com.hegargarcia.mylittleshop.database.AppDatabase
import kotlinx.android.synthetic.main.activity_product_edit.*

class ClientEditActivity : AppCompatActivity() {

    private var db: AppDatabase? = null
    private var clientDao: ClientDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_edit)

        db = AppDatabase?.getDatabase(this)
        clientDao = db?.client()

        updateButton.setOnClickListener{
            editClient()
        }

        deleteButton.setOnClickListener{
            deleteClient()
        }
    }

    private fun editClient(){

    }

    private fun deleteClient(){

    }
}
