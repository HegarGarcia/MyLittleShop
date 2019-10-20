package com.hegargarcia.mylittleshop.sell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hegargarcia.mylittleshop.R
import com.hegargarcia.mylittleshop.dao.SellDao
import com.hegargarcia.mylittleshop.database.AppDatabase
import kotlinx.android.synthetic.main.activity_sell_form.*

class SellFormActivity : AppCompatActivity() {

    private var db: AppDatabase? = null
    private var sellDao: SellDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_form)

        db = AppDatabase?.getDatabase(this)
        sellDao = db?.sell()

        updateButton.setOnClickListener{
            addSell()
        }

        cancelButton.setOnClickListener{
            finish()
        }
    }

    private fun addSell(){

    }
}
