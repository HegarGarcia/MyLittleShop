package com.hegargarcia.mylittleshop.sell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hegargarcia.mylittleshop.R
import android.content.Intent
import android.widget.ArrayAdapter
import com.hegargarcia.mylittleshop.database.AppDatabase
import com.hegargarcia.mylittleshop.entity.Sell
import kotlinx.android.synthetic.main.activity_sell_list.*

class SellListActivity : AppCompatActivity() {

    companion object {
        private const val FORM_ACTIVITY_CODE = 10
    }

    private var sellList: List<Sell>? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        showSellOnListView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_list)
        showSellOnListView()

        toolbar_sell_list.setNavigationOnClickListener {
            finish()
        }

        addSellButton.setOnClickListener{
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
            sellDao?.getAll()
        }

        val sellsAdapter =
            ArrayAdapter<Sell>(this, android.R.layout.simple_list_item_1, sellList!!)
        sellListView.adapter = sellsAdapter
    }
}
