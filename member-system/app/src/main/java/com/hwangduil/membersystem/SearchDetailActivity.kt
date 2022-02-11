package com.hwangduil.membersystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SearchDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_detail)

        val memberNumDetail = findViewById<TextView>(R.id.memberNumDetail)
        val memberNameDetail = findViewById<TextView>(R.id.memberNameDetail)
        val memberAgeDetail = findViewById<TextView>(R.id.memberAgeDetail)
        val memberLocationDetail = findViewById<TextView>(R.id.memberLocationDetail)
        val goBackSearchBtn = findViewById<Button>(R.id.goBackSearch)

        goBackSearchBtn.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }
}