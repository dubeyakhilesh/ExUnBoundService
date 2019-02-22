package com.example.exunboundservice

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.widget.Toast

class MyService : Service() {
    lateinit var handler: Handler
    lateinit var runner: Runner
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        handler = Handler()
        runner = Runner()
        Toast.makeText(this@MyService, "onCreate", Toast.LENGTH_SHORT).show()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        handler.post(runner)
        Toast.makeText(this@MyService, "onStart", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler.post(runner)
        Toast.makeText(this@MyService, "onStartCommand", Toast.LENGTH_SHORT).show()
        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runner)
        Toast.makeText(this@MyService, "onDestroy", Toast.LENGTH_SHORT).show()
    }

    inner class Runner: Runnable{
        override fun run() {
            Toast.makeText(this@MyService, "Runnable", Toast.LENGTH_SHORT).show()
            handler.postDelayed(runner, 5000)
        }

    }
}
