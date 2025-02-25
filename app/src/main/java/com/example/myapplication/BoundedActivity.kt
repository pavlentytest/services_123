package com.example.myapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BoundedActivity : AppCompatActivity() {

    lateinit var mBoundService: BoundService
    var mServiceBound = false


    private val mServiceConnection = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName) {
            mServiceBound = false
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val myBinder = service as BoundService.MyBinder
            mBoundService = myBinder.service
            mServiceBound = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bounded)


        findViewById<Button>(R.id.button1).setOnClickListener {
            val intent = Intent(this, BoundService::class.java)
            bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
        }

        findViewById<Button>(R.id.button2).setOnClickListener {

            if (mServiceBound) {
                unbindService(mServiceConnection)
                mServiceBound = false
            }
            val intent = Intent(this, BoundService::class.java)
            stopService(intent)
        }


        findViewById<Button>(R.id.button3).setOnClickListener{
            findViewById<TextView>(R.id.timer).setText(mBoundService.timestamp)
        }
    }

    override fun onStop() {
        super.onStop()
        if (mServiceBound) {
            unbindService(mServiceConnection)
            mServiceBound = false
        }
    }
}