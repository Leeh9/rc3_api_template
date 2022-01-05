package com.example.rc3_api_template.src.main

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.example.rc3_api_template.databinding.ActivityMainBinding
import com.example.rc3_api_template.R
import com.example.rc3_api_template.config.BaseActivity
import com.example.rc3_api_template.src.main.fav.FavFragment

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.rc3_api_template.src.main.map.MapFragment
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        getHashKey()

        // add
        supportFragmentManager.beginTransaction().replace(R.id.framelayout, MapFragment()).commitAllowingStateLoss()

        binding.bottomNav.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_map -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.framelayout, MapFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_fav -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.framelayout, FavFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            })
    }

//    private fun getHashKey() {
//        var packageInfo: PackageInfo? = null
//        try {
//            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
//        } catch (e: PackageManager.NameNotFoundException) {
//            e.printStackTrace()
//        }
//        if (packageInfo == null) Log.e("KeyHash", "KeyHash:null")
//        for (signature in packageInfo!!.signatures) {
//            try {
//                val md: MessageDigest = MessageDigest.getInstance("SHA")
//                md.update(signature.toByteArray())
//                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT))
//            } catch (e: NoSuchAlgorithmException) {
//                Log.e("KeyHash", "Unable to get MessageDigest. signature=$signature", e)
//            }
//        }
//    }

}

