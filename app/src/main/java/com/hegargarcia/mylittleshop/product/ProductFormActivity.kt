package com.hegargarcia.mylittleshop.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hegargarcia.mylittleshop.R
import com.hegargarcia.mylittleshop.dao.ProductDao
import com.hegargarcia.mylittleshop.database.AppDatabase
import com.hegargarcia.mylittleshop.entity.Product
import kotlinx.android.synthetic.main.activity_product_form.*

class ProductFormActivity : AppCompatActivity() {

    private var productDao: ProductDao? = null
    private var product: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_form)

        productDao = AppDatabase.getDatabase(this).let {
            it?.product()
        }

        val productId = intent.getIntExtra("product.id", -1)

        if (productId != -1) {
            product = productDao?.getById(productId)!!
            namePrompt.setText(product?.name)
            descriptionPrompt.setText(product?.description)
            amountPrompt.setText(product?.amount.toString())
            pricePrompt.setText(product?.price.toString())
            costPrompt.setText(product?.cost.toString())
            photoPrompt.setText(product?.photoUrl)
            deleteButton.visibility = View.VISIBLE
        }

        updateButton.setOnClickListener {
            addProduct()
        }

        cancelButton.setOnClickListener {
            finish()
        }

        deleteButton.setOnClickListener {
            deleteClient()
        }
    }

    private fun addProduct(){
        if (product?.id != null) {
            product.apply {
                this?.name = namePrompt.text.toString()
                this?.description = descriptionPrompt.text.toString()
                this?.photoUrl = photoPrompt.text.toString()
                this?.amount = amountPrompt.text.toString().toInt()
                this?.cost = costPrompt.text.toString().toFloat()
                this?.price = pricePrompt.text.toString().toFloat()
            }

            productDao?.update(product!!)
        } else {
            product = Product(
                name = namePrompt.text.toString(),
                description = descriptionPrompt.text.toString(),
                photoUrl = photoPrompt.text.toString(),
                amount = amountPrompt.text.toString().toInt(),
                cost = costPrompt.text.toString().toFloat(),
                price = pricePrompt.text.toString().toFloat()
            )

            productDao?.insert(product!!)
        }

        if (product != null) finish()
    }

    private fun deleteClient(){
        productDao?.delete(product!!)
        finish()
    }
}
