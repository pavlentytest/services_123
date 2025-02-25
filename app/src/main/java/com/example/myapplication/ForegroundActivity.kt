package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class ForegroundActivity : AppCompatActivity() {
    companion object {
        const val MAIN_ACTION: String = "com.samples.services.foreground.action.main"
        const val INIT_ACTION: String = "com.samples.services.foreground.action.init"
        const val PREV_ACTION: String = "com.samples.services.foreground.action.prev"
        const val PLAY_ACTION: String = "com.samples.services.foreground.action.play"
        const val NEXT_ACTION: String = "com.samples.services.foreground.action.next"
        const val STARTFOREGROUND_ACTION: String =
            "com.samples.services.foreground.action.startforeground"
        const val STOPFOREGROUND_ACTION: String =
            "com.samples.services.foreground.action.stopforeground"
        const val FOREGROUND_SERVICE: Int = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground)

    }

    fun onButtonClick(v: View) {
        val service = Intent(this@ForegroundActivity, ForegroundService::class.java)
        when (v.id) {
            R.id.button1 -> {
                service.action = STARTFOREGROUND_ACTION
                ForegroundService.IS_SERVICE_RUNNING = true
            }

            R.id.button2 -> {
                service.action = STOPFOREGROUND_ACTION
                ForegroundService.IS_SERVICE_RUNNING = false
            }
        }

        startService(service)
    }
}