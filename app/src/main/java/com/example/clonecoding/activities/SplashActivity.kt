package com.example.clonecoding

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.clonecoding.databinding.ActivitySplashBinding
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    private var auth: FirebaseAuth? = null
    private var preferInfo: SharedPreferences? = null
    private lateinit var binding: ActivitySplashBinding
    companion object {
        private const val DURATION : Long = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        preferInfo = getSharedPreferences("authorization", MODE_PRIVATE)

        val token = preferInfo?.getString("token", null).toString()
        Log.i("SplashActivity", auth?.currentUser.toString())

        Handler(Looper.getMainLooper()).postDelayed({
            if(auth?.currentUser != null ||  (checkValidToken(token)) ) {
                movePage(MainActivity::class.java)
            } else movePage(LoginActivity::class.java)
        }, DURATION)
    }

    private fun <T> movePage(activity: Class<T>) {
        var intent = Intent(this, activity)
        startActivity(intent)
        finish()
    }

    private fun checkValidToken(token: String): Boolean {
        Log.i("SplashActivity", token)
        return false
    }
}