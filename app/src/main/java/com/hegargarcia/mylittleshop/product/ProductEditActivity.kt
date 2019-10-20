package com.hegargarcia.mylittleshop.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hegargarcia.mylittleshop.R
import com.hegargarcia.mylittleshop.dao.ProductDao
import com.hegargarcia.mylittleshop.database.AppDatabase
import kotlinx.android.synthetic.main.activity_product_edit.*

class ProductEditActivity : AppCompatActivity() {

    private var db: AppDatabase? = null
    private var productDao: ProductDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_edit)

        db = AppDatabase.getDatabase(this)
        productDao = db?.product()

        updateButton.setOnClickListener{
            editProduct()
        }

        cancelButton.setOnClickListener{
            deleteProduct()
        }
    }

    private fun editProduct(){

    }

    private fun deleteProduct(){

    }
}
