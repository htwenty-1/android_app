package com.hwangduil.membersystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import dbHelper.DBHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val insertBtn = findViewById<Button>(R.id.addMember)
        val delBtn = findViewById<Button>(R.id.delMember)
        val searchBtn = findViewById<Button>(R.id.searchMember)
        val editBtn = findViewById<Button>(R.id.editMember)
        val allBtn = findViewById<Button>(R.id.allMember)

        insertBtn.setOnClickListener {
            val intent = Intent(this, InsertActivity::class.java)
            startActivity(intent)
        }

        delBtn.setOnClickListener {
            val intent = Intent(this, DeleteActivity::class.java)
            startActivity(intent)
        }

        searchBtn.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        allBtn.setOnClickListener {
            val intent = Intent(this, AllMemberActivity::class.java)
            startActivity(intent)
        }

    }
}