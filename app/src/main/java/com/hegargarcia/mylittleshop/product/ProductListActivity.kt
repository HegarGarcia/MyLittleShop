package com.hegargarcia.mylittleshop.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.hegargarcia.mylittleshop.R
import com.hegargarcia.mylittleshop.database.AppDatabase
import com.hegargarcia.mylittleshop.entity.Product
import kotlinx.android.synthetic.main.activity_product_list.*

class ProductListActivity : AppCompatActivity() {

    companion object {
        private const val FORM_ACTIVITY_CODE = 10
    }

    private var productList: List<Product>? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        showProductOnListView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        showProductOnListView()

        addProductButton.setOnClickListener{
            val intento = Intent(this, ProductFormActivity::class.java)
            startActivityForResult(intento, FORM_ACTIVITY_CODE)
        }

        productListView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, ProductFormActivity::class.java)
            val selectedProduct = productList?.get(position)
            intent.putExtra("product.id", selectedProduct?.id)
            startActivityForResult(intent, FORM_ACTIVITY_CODE)
        }
    }
    private fun showProductOnListView() {
        productList = AppDatabase.getDatabase(this).let {
            val productDao = it?.product()
            productDao?.getAll()
        }

        val productsAdapter =
            ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, productList!!)
        productListView.adapter = productsAdapter
    }
}
