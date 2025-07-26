package com.contact.contactapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.contact.contactapp.databinding.ActivitySplashScreenBinding

class SplashScreenActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val handler= Handler(mainLooper)
        handler.postDelayed({
            val intent= Intent (this@SplashScreenActivity, ContactActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}