package com.hegargarcia.mylittleshop.sell

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.hegargarcia.mylittleshop.R
import com.hegargarcia.mylittleshop.dao.ClientDao
import com.hegargarcia.mylittleshop.dao.ProductDao
import com.hegargarcia.mylittleshop.dao.SellDao
import com.hegargarcia.mylittleshop.database.AppDatabase
import com.hegargarcia.mylittleshop.entity.Client
import com.hegargarcia.mylittleshop.entity.Product
import kotlinx.android.synthetic.main.activity_sell_form.*

class SellFormActivity : AppCompatActivity() {

    private var sellDao: SellDao? = null
    private var clientDao: ClientDao? = null
    private var productDao: ProductDao? = null

    private var clientList: List<Client>? = null
    private var productList: List<Product>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_form)

        AppDatabase.getDatabase(this).also {
            sellDao = it?.sell()
            clientDao = it?.client()
            productDao = it?.product()
        }

        initializeSpinners()

        addButton.setOnClickListener {
            addSell()
        }

        deleteButton.setOnClickListener {
            deleteSell()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun addSell() {

    }

    private fun deleteSell() {

    }

    private fun showClients() {
        clientList = clientDao?.getAll()

        clientSpinner.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item,
            clientList!!.map {
                it.name
            }).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }

    private fun showProducts() {
        productList = productDao?.getAll()

        productSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            productList!!.map {
                "${it.description} - $${it.cost}"
            }).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }

    private fun initializeSpinners() {
        showClients()
        showProducts()
    }
}
