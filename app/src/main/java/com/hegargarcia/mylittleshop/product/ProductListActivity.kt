package com.hegargarcia.mylittleshop.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hegargarcia.mylittleshop.R
import kotlinx.android.synthetic.main.activity_product_list.*

class ProductListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        addProductButton.setOnClickListener{
            val intento = Intent(this, ProductFormActivity::class.java)
            startActivity(intento)
        }
    }
}
