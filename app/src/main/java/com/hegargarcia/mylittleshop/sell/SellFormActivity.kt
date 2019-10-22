package com.hegargarcia.mylittleshop.sell

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.hegargarcia.mylittleshop.R
import com.hegargarcia.mylittleshop.dao.ClientDao
import com.hegargarcia.mylittleshop.dao.ProductDao
import com.hegargarcia.mylittleshop.dao.SellDao
import com.hegargarcia.mylittleshop.database.AppDatabase
import com.hegargarcia.mylittleshop.entity.Client
import com.hegargarcia.mylittleshop.entity.Product
import com.hegargarcia.mylittleshop.entity.Sell
import kotlinx.android.synthetic.main.activity_sell_form.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class SellFormActivity : AppCompatActivity() {

    private var sellDao: SellDao? = null
    private var clientDao: ClientDao? = null
    private var productDao: ProductDao? = null

    private var clientList: List<Client>? = null
    private var productList: List<Product>? = null

    private var sell: Sell? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_form)

        AppDatabase.getDatabase(this).also {
            sellDao = it?.sell()
            clientDao = it?.client()
            productDao = it?.product()
        }

        initializeSpinners()

        val sellId = intent.getIntExtra("sell.id", -1)

        if (sellId != -1) {
            sell = sellDao?.getById(sellId)

            clientList?.indexOfFirst {
                it.id!! == sell?.client?.toInt()!!
            }?.let { clientSpinner.setSelection(it) }

            productList?.indexOfFirst {
                it.id!! == sell?.product?.toInt()!!
            }?.let { productSpinner.setSelection(it) }

            amountPrompt.setText(sell?.amount.toString())

            deleteButton.visibility = View.VISIBLE
        } else {
            deleteButton.visibility = View.GONE
        }

        addButton.setOnClickListener {
            addSell()
        }

        deleteButton.setOnClickListener {
            deleteSell()
        }

        toolbar_sell_form.setNavigationOnClickListener {
            finish()
        }
    }

    private fun addSell() {
        val clientIndex = clientSpinner.selectedItemPosition
        val productIndex = productSpinner.selectedItemPosition

        val client = clientList!![clientIndex]
        val product = productList!![productIndex]
        val amount = amountPrompt.text.toString().toInt()
        val cost = product.cost * amount
        val total = product.price * amount
        val date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())

        val sell = Sell(
            client = client.id.toString(),
            product = product.id.toString(),
            amount = amount,
            cost = cost,
            date = date,
            total = total
        )

        product.amount -= amount

        sellDao?.insert(sell)
        productDao?.update(product)
        finish()
    }

    private fun deleteSell() {
        sellDao?.delete(sell!!)
        finish()
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
