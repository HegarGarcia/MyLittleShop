package com.hegargarcia.mylittleshop.sell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hegargarcia.mylittleshop.R
import android.content.Intent
import kotlinx.android.synthetic.main.activity_sell_list.*

class SellListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_list)

        sellButton.setOnClickListener{
            val intento = Intent(this, SellFormActivity::class.java)
            startActivity(intento)
        }
    }
}
