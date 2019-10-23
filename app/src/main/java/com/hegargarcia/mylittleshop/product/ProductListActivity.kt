package com.hegargarcia.mylittleshop.product

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.hegargarcia.mylittleshop.R
import com.hegargarcia.mylittleshop.authentication.Auth
import com.hegargarcia.mylittleshop.database.AppDatabase
import com.hegargarcia.mylittleshop.entity.Product
import kotlinx.android.synthetic.main.activity_product_list.*

class ProductListActivity : AppCompatActivity() {

    companion object {
        private const val FORM_ACTIVITY_CODE = 10
    }

    private var productList: List<Product>? = null
    private var storeName: String? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        showProductOnListView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        showProductOnListView()

        storeName = Auth(this).getCurrentUser?.storeName

        toolbar_product_list.setNavigationOnClickListener {
            finish()
        }

        addProductButton.setOnClickListener{
            val intent = Intent(this, ProductFormActivity::class.java)
            startActivityForResult(intent, FORM_ACTIVITY_CODE)
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
            productDao?.getAll(storeName!!)
        }

        val productsAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, productList?.map { "${it.name} -$${it.price}" }!!)
        productListView.adapter = productsAdapter
    }
}
