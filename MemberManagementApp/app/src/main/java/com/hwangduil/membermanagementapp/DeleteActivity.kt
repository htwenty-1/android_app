package com.hwangduil.membermanagementapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hwangduil.membermanagementapp.databinding.ActivityDeleteBinding
import dbHelper.DBHelper

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityDeleteBinding

class DeleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.memberDelBtn.setOnClickListener {
            val name = binding.deleteName.text.toString()
            val num = binding.deleteNumber.text.toString().toInt()

            try {
                DBHelper.getInstance(this, "members.db")?.delete(name, num)
                Toast.makeText(this, "${name}님의 정보가 삭제되었습니다", Toast.LENGTH_SHORT).show()
            } catch (e:Exception) {
                e.printStackTrace()
                Toast.makeText(this, "일치하는 조건이 없습니다. \n입력한 내용을 다시 확인하세요.", Toast.LENGTH_SHORT).show()
            }

        }
    }
}