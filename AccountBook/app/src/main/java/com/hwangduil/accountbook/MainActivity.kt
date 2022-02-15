package com.hwangduil.accountbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hwangduil.accountbook.databinding.ActivityMainBinding
import view.AddActivity
import view.SearchActivity
import view.SearchPeriodActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addBtn.setOnClickListener(this)
        binding.searchBtn.setOnClickListener(this)
        binding.searchWithPeriodBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.addBtn -> {
                val intent = Intent(this, AddActivity::class.java)
                startActivity(intent)
            }
            binding.searchBtn -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
            binding.searchWithPeriodBtn -> {
                val intent = Intent(this, SearchPeriodActivity::class.java)
                startActivity(intent)
            }
        }
    }
}