package com.hegargarcia.mylittleshop.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hegargarcia.mylittleshop.R
import com.hegargarcia.mylittleshop.dao.ProductDao
import com.hegargarcia.mylittleshop.database.AppDatabase
import com.hegargarcia.mylittleshop.entity.Product
import kotlinx.android.synthetic.main.activity_product_form.*

class ProductFormActivity : AppCompatActivity() {

    private var db: AppDatabase? = null
    private var productDao: ProductDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_form)

        db = AppDatabase.getDatabase(this)
        productDao = db?.product()

        updateButton.setOnClickListener{
            addProduct()
        }

        cancelButton.setOnClickListener{
            finish()
        }
    }

    private fun addProduct(){
        val product = Product(
            name = namePrompt.text.toString(),
            description = descriptionPrompt.text.toString(),
            amount = amountPrompt.text.toString().toInt(),
            price = pricePrompt.text.toString().toFloat(),
            cost = costPrompt.text.toString().toFloat(),
            photoUrl = photoPrompt.text.toString()
        )
        productDao?.insert(product)

        Toast.makeText(this,"Se ha añadido el producto", Toast.LENGTH_LONG).show()
        finish()
    }
}
