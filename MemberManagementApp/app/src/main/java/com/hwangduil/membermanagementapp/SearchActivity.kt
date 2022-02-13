package com.hwangduil.membermanagementapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hwangduil.membermanagementapp.databinding.ActivityDeleteBinding
import com.hwangduil.membermanagementapp.databinding.ActivitySearchBinding

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}