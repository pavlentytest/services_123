package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {

            R.id.button1 -> {
                val nextActivity = Intent(this, UnboundedActivity::class.java)
                startActivity(nextActivity)
            }
            R.id.button2 -> {
                val nextActivity = Intent(this, BoundedActivity::class.java)
                startActivity(nextActivity)
            }
            R.id.button4 -> {
                val nextActivity = Intent(this, ForegroundActivity::class.java)
                startActivity(nextActivity)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button1).setOnClickListener(clickListener)
        findViewById<Button>(R.id.button2).setOnClickListener(clickListener)
        findViewById<Button>(R.id.button4).setOnClickListener(clickListener)

    }
}