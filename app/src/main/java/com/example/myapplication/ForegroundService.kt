package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat



class ForegroundService : Service() {

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
        private val LOG_TAG = "ForegroundService"
        var IS_SERVICE_RUNNING = false
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        when {
            STARTFOREGROUND_ACTION == intent.action -> {
                showNotification()
                Toast.makeText(this, "Service Started!", Toast.LENGTH_SHORT).show()

            }
            PREV_ACTION == intent.action -> Toast.makeText(this, "Clicked Previous!", Toast.LENGTH_SHORT)
                .show()
            PLAY_ACTION == intent.action -> Toast.makeText(this, "Clicked Play!", Toast.LENGTH_SHORT).show()
            NEXT_ACTION == intent.action -> Toast.makeText(this, "Clicked Next!", Toast.LENGTH_SHORT).show()
            STOPFOREGROUND_ACTION == intent.action -> {
                stopForeground(true)
                stopSelf()
            }
        }
        return Service.START_STICKY
    }

    private fun showNotification() {
        val notificationIntent = Intent(this, MainActivity::class.java)
        notificationIntent.action = Intent.ACTION_MAIN
        notificationIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(this, 0,
            notificationIntent, PendingIntent.FLAG_IMMUTABLE)

        val previousIntent = Intent(this, ForegroundService::class.java)
        previousIntent.action = PREV_ACTION
        val ppreviousIntent = PendingIntent.getService(this, 0,
            previousIntent, PendingIntent.FLAG_IMMUTABLE)

        val playIntent = Intent(this, ForegroundService::class.java)
        playIntent.action = PLAY_ACTION
        val pplayIntent = PendingIntent.getService(this, 0,
            playIntent, PendingIntent.FLAG_IMMUTABLE)

        val nextIntent = Intent(this, ForegroundService::class.java)
        nextIntent.action = NEXT_ACTION
        val pnextIntent = PendingIntent.getService(this, 0,
            nextIntent, PendingIntent.FLAG_IMMUTABLE)

        val icon = BitmapFactory.decodeResource(applicationContext.resources,
            R.drawable.ic_launcher)


        val notification = NotificationCompat.Builder(this)
            .setContentTitle("Sample Music Player")
            .setTicker("Music Player")
            .setContentText("My Playlist Song")
            .setSmallIcon(R.drawable.ic_launcher)
            .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, false))
            .setContentIntent(pendingIntent)
            .addAction(android.R.drawable.ic_media_previous, "Previous",
                ppreviousIntent)
            .addAction(android.R.drawable.ic_media_play, "Play",
                pplayIntent)
            .addAction(android.R.drawable.ic_media_next, "Next",
                pnextIntent).build()
        startForeground(FOREGROUND_SERVICE, notification)
    }


}