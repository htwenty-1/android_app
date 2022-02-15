package com.hwangduil.membermanagementapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import com.hwangduil.membermanagementapp.databinding.ActivityPrintAllMemberBinding

@SuppressLint("StaticFieldLeak")
private lateinit var binding:ActivityPrintAllMemberBinding

class PrintAllMemberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrintAllMemberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tableLayout = binding.allPrintTable
        var tableRow: TableRow? = null
        var textView: TextView? = null

        val range = 8
        for (i in 0 until range) {

        }
    }

}