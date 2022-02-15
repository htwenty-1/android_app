package com.hwangduil.membersystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import dbHelper.DBHelper

class InsertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        val dbHelper = DBHelper(this, "member_db.db", null, 1)
        val db = dbHelper.writableDatabase

        val userName = findViewById<EditText>(R.id.nameInput)
        val userAge = findViewById<EditText>(R.id.ageInput)
        val addressInput = findViewById<EditText>(R.id.addressInput)
        val addBtn = findViewById<Button>(R.id.insertMemberBtn)

        addBtn.setOnClickListener {
            dbHelper.insert(db, userName.text.toString(), userAge.text.toString(), addressInput.text.toString())
            Toast.makeText(this, "회원 등록이 완료됨", Toast.LENGTH_SHORT).show()
        }

    }
}