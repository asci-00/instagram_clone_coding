package com.example.clonecoding

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.clonecoding.common.LoadingDialog
import com.example.clonecoding.databinding.ActivityMainBinding
import com.example.clonecoding.views.*
import com.example.clonecoding.views.home.HomeFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener(this)
        binding.bottomNavigation.selectedItemId = R.id.action_home

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_home -> {
                var homeFragment = HomeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, homeFragment).commit()
                return true
            }
            R.id.action_search -> {
                var gridFragment = GridFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, gridFragment).commit()
                return true
            }
            R.id.action_add_photo -> {
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                    startActivity(Intent(this, AddPhotoActivity::class.java))
                return true
            }
            R.id.action_favorite_alarm -> {
                var alarmFragment = AlarmFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, alarmFragment).commit()
                return true
            }
            R.id.action_account -> {
                var userFragment = UserFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, userFragment).commit()
                return true
            }
        }
        return false
    }
}