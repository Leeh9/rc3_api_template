package com.example.rc3_api_template.src.main.login

import android.content.Intent
import android.os.Bundle
import com.example.rc3_api_template.config.BaseActivity
import com.example.rc3_api_template.databinding.ActivityLoginBinding
import com.example.rc3_api_template.src.main.MainActivity


class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btLogin.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}