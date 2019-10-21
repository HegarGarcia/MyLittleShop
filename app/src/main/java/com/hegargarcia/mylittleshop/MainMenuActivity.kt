package com.hegargarcia.mylittleshop

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hegargarcia.mylittleshop.client.ClientListActivity
import com.hegargarcia.mylittleshop.product.ProductListActivity
import com.hegargarcia.mylittleshop.sell.SellListActivity
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        botonCliente.setOnClickListener{
            val intent = Intent(this, ClientListActivity::class.java)
            startActivity(intent)
        }

        botonInventario.setOnClickListener{
            val intent = Intent(this, ProductListActivity::class.java)
            startActivity(intent)
        }

        botonVentas.setOnClickListener{
            val intent = Intent(this, SellListActivity::class.java)
            startActivity(intent)
        }
    }
}
