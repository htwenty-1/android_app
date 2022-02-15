package com.hwangduil.membersystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import dbHelper.DBHelper

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val dbHelper = DBHelper(this, "member_db.db", null, 1)
        val db = dbHelper.writableDatabase

        val userNameInput = findViewById<EditText>(R.id.wantSearchUserNameInput)
        val searchBtn = findViewById<Button>(R.id.goToSearch)
        val userListBox = findViewById<TextView>(R.id.userList)

        searchBtn.setOnClickListener {
            dbHelper.search(db, userNameInput.text.toString())
        }

    }
}