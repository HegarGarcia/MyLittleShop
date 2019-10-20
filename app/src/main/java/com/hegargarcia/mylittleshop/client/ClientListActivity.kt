package com.hegargarcia.mylittleshop.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hegargarcia.mylittleshop.R
import kotlinx.android.synthetic.main.activity_client_list.*

class ClientListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_list)
    }

    private fun setUpToolbar() {
        toolbar.title = getString(R.string.client_list_title)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}
