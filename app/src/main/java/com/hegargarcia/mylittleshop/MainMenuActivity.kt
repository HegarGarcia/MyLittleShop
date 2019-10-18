package com.hegargarcia.mylittleshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.hegargarcia.mylittleshop.client.ClientListActivity
import com.hegargarcia.mylittleshop.product.ProductListActivity
import com.hegargarcia.mylittleshop.sell.SellListActivity
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        botonCliente.setOnClickListener{
            val intento = Intent(this, ClientListActivity::class.java)
            startActivity(intento)
        }

        botonInventario.setOnClickListener{
            val intento = Intent(this, ProductListActivity::class.java)
            startActivity(intento)
        }

        botonVentas.setOnClickListener{
            val intento = Intent(this, SellListActivity::class.java)
            startActivity(intento)
        }
    }
}
