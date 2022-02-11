package com.hwangduil.membersystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import dbHelper.DBHelper

class DeleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        val dbHelper = DBHelper(this, "member_db.db", null, 1)
        val db = dbHelper.writableDatabase

        val nameInput = findViewById<EditText>(R.id.wantDeleteNameInput)
        val delBtn = findViewById<Button>(R.id.wantDeleteBtn)

        delBtn.setOnClickListener {
            dbHelper.delete(db, nameInput.text.toString())
            Toast.makeText(this, "${nameInput.text}님과 관련된 모든 데이터가 삭제됨.", Toast.LENGTH_LONG).show()
        }

    }
}