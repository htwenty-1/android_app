package com.hwangduil.membermanagementapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hwangduil.membermanagementapp.databinding.ActivityInsertBinding
import dbHelper.DBHelper
import vo.Member

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityInsertBinding

class InsertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.memberInsertBtn.setOnClickListener {
            val member = Member(binding.insertName.text.toString(),
                                binding.insertAge.text.toString().toInt(),
                                binding.insertLocation.text.toString())

            DBHelper.getInstance(this, "members.db")?.insert(member)
            Toast.makeText(this, "${member.name}님이 신규회원으로 등록됨", Toast.LENGTH_SHORT).show()
        }

    }
}