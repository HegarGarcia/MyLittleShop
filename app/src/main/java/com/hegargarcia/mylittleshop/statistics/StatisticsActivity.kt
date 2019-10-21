package com.hegargarcia.mylittleshop.statistics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hegargarcia.mylittleshop.R
import com.hegargarcia.mylittleshop.dao.SellDao
import com.hegargarcia.mylittleshop.database.AppDatabase
import kotlinx.android.synthetic.main.activity_statictics.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class StatisticsActivity : AppCompatActivity() {

    private var sellDao: SellDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statictics)

        sellDao = AppDatabase.getDatabase(this)?.sell()

        val today = LocalDateTime.now()
        fromPrompt.setText(today.toString())
        toPrompt.setText(today.toString())

        dailyResultLabel.text = getString(
            R.string.money, sellDao?.findSellsBetweenDates(
                Date.from(today.atZone(ZoneId.systemDefault()).toInstant()),
                Date.from(today.minusDays(1).atZone(ZoneId.systemDefault()).toInstant())
            )?.map {
                it.total - it.cost
            }?.sum()
        )

        monthlyResultLabel.text = getString(
            R.string.money, sellDao?.findSellsBetweenDates(
                Date.from(today.atZone(ZoneId.systemDefault()).toInstant()),
                Date.from(today.minusDays(30).atZone(ZoneId.systemDefault()).toInstant())
            )?.map {
                it.total - it.cost
            }?.sum()
        )

        yearlyResultLabel.text = getString(
            R.string.money, sellDao?.findSellsBetweenDates(
                Date.from(today.atZone(ZoneId.systemDefault()).toInstant()),
                Date.from(today.minusDays(365).atZone(ZoneId.systemDefault()).toInstant())
            )?.map {
                it.total - it.cost
            }?.sum()
        )

        calculateButton.setOnClickListener {
            val from =
                Date.from(LocalDate.parse(fromPrompt.text).atStartOfDay(ZoneId.systemDefault()).toInstant())
            val to =
                Date.from(LocalDate.parse(toPrompt.text).atStartOfDay(ZoneId.systemDefault()).toInstant())

            customResultLabel.text = getString(
                R.string.money,
                sellDao?.findSellsBetweenDates(
                    from,
                    to
                )?.map {
                    it.total - it.cost
                }?.sum()
            )
        }
    }
}
