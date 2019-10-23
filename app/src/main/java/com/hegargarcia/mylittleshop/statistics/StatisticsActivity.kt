package com.hegargarcia.mylittleshop.statistics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hegargarcia.mylittleshop.R
import com.hegargarcia.mylittleshop.authentication.Auth
import com.hegargarcia.mylittleshop.dao.SellDao
import com.hegargarcia.mylittleshop.database.AppDatabase
import kotlinx.android.synthetic.main.activity_statictics.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class StatisticsActivity : AppCompatActivity() {

    private var sellDao: SellDao? = null
    private var storeName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statictics)

        sellDao = AppDatabase.getDatabase(this)?.sell()
        storeName = Auth(this).getCurrentUser?.storeName

        val today = LocalDateTime.now()

        fromPrompt.setText(today.format(DateTimeFormatter.ISO_DATE))
        toPrompt.setText(today.format(DateTimeFormatter.ISO_DATE))

        dailyResultLabel.text = getString(
            R.string.money, sellDao?.findSellsBetweenDates(
                today,
                today.minusDays(1),
                storeName!!
            )?.map {
                it.total - it.cost
            }?.sum()
        )

        monthlyResultLabel.text = getString(
            R.string.money, sellDao?.findSellsBetweenDates(
                today,
                today.minusMonths(1),
                storeName!!
            )?.map {
                it.total - it.cost
            }?.sum()
        )

        yearlyResultLabel.text = getString(
            R.string.money, sellDao?.findSellsBetweenDates(
                today,
                today.minusYears(1),
                storeName!!
            )?.map {
                it.total - it.cost
            }?.sum()
        )

        calculateButton.setOnClickListener {
            val from = LocalDateTime.parse(fromPrompt.text)
            val to = LocalDateTime.parse(toPrompt.text)

            customResultLabel.text = getString(
                R.string.money,
                sellDao?.findSellsBetweenDates(
                    from,
                    to,
                    storeName!!
                )?.map {
                    it.total - it.cost
                }?.sum()
            )
        }

        toolbar_statistics.setNavigationOnClickListener {
            finish()
        }
    }
}
