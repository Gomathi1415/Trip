//package com.example.trip
//
//import android.content.Intent
//import android.os.Bundle
//import android.os.Handler
//import android.support.v7.app.AppCompatActivity
//
//class SplashActivity : AppCompatActivity() {
//    val SPLASH_TIME_OUT: Long = 1000L
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.splash_activity)
//        Handler().postDelayed(
//            Runnable
//
//            {
//
//                val i = Intent(this@SplashActivity, MainActivity::class.java)
//                startActivity(i)
//
//                finish()
//            }, SPLASH_TIME_OUT
//        )
//    }
//}