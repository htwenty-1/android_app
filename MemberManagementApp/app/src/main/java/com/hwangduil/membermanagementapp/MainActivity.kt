package com.hwangduil.membermanagementapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.hwangduil.membermanagementapp.databinding.ActivityMainBinding

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.insertBtn.setOnClickListener(this)
        binding.deleteBtn.setOnClickListener(this)
        binding.searchBtn.setOnClickListener(this)
        binding.printAll.setOnClickListener(this)

    }

    override fun onClick(view: View?) {

        when (view) {
            binding.insertBtn -> {
                val intent = Intent(this, InsertActivity::class.java)
                startActivity(intent)
            }
            binding.deleteBtn -> {
                Log.d("btn clicked", "clicked")
                val intent = Intent(this, DeleteActivity::class.java)
                startActivity(intent)
            }
            binding.searchBtn -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
            binding.printAll -> {
                val intent = Intent(this, PrintAllMemberActivity::class.java)
                startActivity(intent)
            }
        }
    }
}