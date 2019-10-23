package com.hegargarcia.mylittleshop.sell

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.hegargarcia.mylittleshop.R
import com.hegargarcia.mylittleshop.authentication.Auth
import com.hegargarcia.mylittleshop.database.AppDatabase
import com.hegargarcia.mylittleshop.entity.Sell
import kotlinx.android.synthetic.main.activity_sell_list.*

class SellListActivity : AppCompatActivity() {

    companion object {
        private const val FORM_ACTIVITY_CODE = 10
    }

    private var sellList: List<Sell>? = null
    private var storeName: String? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        showSellOnListView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_list)
        storeName = Auth(this).getCurrentUser?.storeName
        showSellOnListView()



        toolbar_sell_list.setNavigationOnClickListener {
            finish()
        }

        addSellButton.setOnClickListener {
            val intent = Intent(this, SellFormActivity::class.java)
            startActivityForResult(intent, FORM_ACTIVITY_CODE)
        }

        sellListView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, SellFormActivity::class.java)
            val selectedSell = sellList?.get(position)
            intent.putExtra("sell.id", selectedSell?.id)
            startActivityForResult(intent, FORM_ACTIVITY_CODE)
        }
    }

    private fun showSellOnListView() {
        sellList = AppDatabase.getDatabase(this).let {
            val sellDao = it?.sell()
            sellDao?.getAll(storeName!!)
        }

        val sellsAdapter =
            sellList?.map { sell ->
                val client = AppDatabase.getDatabase(this).let {
                    it?.client()?.getById(sell.client.toInt(), storeName!!)
                }

                "${client?.name} - $${sell.total}"
            }?.let {
                ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    it
                )
            }

        sellListView.adapter = sellsAdapter
    }
}
