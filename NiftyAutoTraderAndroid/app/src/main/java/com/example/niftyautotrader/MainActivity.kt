package com.example.niftyautotrader

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color

class MainActivity : AppCompatActivity() {
    private var paperMode = true
    private var dailyPnl = 0.0
    private var trades = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(28, 28, 28, 28)
        }

        fun tv(text: String, size: Float = 16f): TextView =
            TextView(this).apply {
                this.text = text
                textSize = size
                setPadding(0, 10, 0, 10)
            }

        root.addView(tv("📈 NIFTY AUTO TRADER", 25f))
        root.addView(tv("Upstox • NIFTY only", 15f))

        val mode = tv("🟢 PAPER TRADING", 18f)
        mode.setTextColor(Color.rgb(0, 120, 70))
        root.addView(mode)

        root.addView(tv("Capital: ₹10,000"))
        root.addView(tv("Maximum daily loss: ₹800"))
        root.addView(tv("Maximum trades: 3"))
        root.addView(tv("Strategy: Option Buying (EMA 9/21)"))

        val pnl = tv("Today's P&L: ₹0.00")
        root.addView(pnl)

        val signal = tv("Signal: WAIT", 22f)
        root.addView(signal)

        val start = Button(this).apply { text = "START PAPER TRADING" }
        root.addView(start)

        val stop = Button(this).apply { text = "🛑 EMERGENCY STOP" }
        root.addView(stop)

        val api = Button(this).apply { text = "CONNECT UPSTOX API" }
        root.addView(api)

        val note = tv(
            "Safety: Live orders are disabled in this starter Android build. " +
            "Connect and test paper trading first. Actual ₹800 loss cannot be guaranteed " +
            "because of slippage, gaps, rejected orders or connectivity issues."
        )
        root.addView(note)

        start.setOnClickListener {
            paperMode = true
            signal.text = "Signal: WAIT — monitoring NIFTY"
            Toast.makeText(this, "Paper trading started", Toast.LENGTH_SHORT).show()
        }

        stop.setOnClickListener {
            signal.text = "Signal: STOPPED"
            Toast.makeText(this, "Emergency stop activated", Toast.LENGTH_SHORT).show()
        }

        api.setOnClickListener {
            Toast.makeText(
                this,
                "Upstox OAuth/API integration is the next module.",
                Toast.LENGTH_LONG
            ).show()
        }

        setContentView(root)
    }
}
