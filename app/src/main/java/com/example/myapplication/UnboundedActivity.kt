package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UnboundedActivity : AppCompatActivity() {
    private val clickListener = View.OnClickListener { view ->
        val intent = Intent(this, UnboundedService::class.java)
        when (view.id) {

            R.id.button1 -> {
                startService(intent)
            }
            R.id.button2 -> {
                stopService(intent)
            }
            R.id.button3 -> {
                val nextActivity = Intent(this, BoundedActivity::class.java)
                startActivity(nextActivity)
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unbounded)
        findViewById<Button>(R.id.button1).setOnClickListener(clickListener)
        findViewById<Button>(R.id.button2).setOnClickListener(clickListener)
        findViewById<Button>(R.id.button3).setOnClickListener(clickListener)
    }
}