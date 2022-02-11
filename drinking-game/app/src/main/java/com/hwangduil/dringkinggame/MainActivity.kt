package com.hwangduil.dringkinggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpSpinnerItem()

        val spinner = findViewById<Spinner>(R.id.spinner)
        val setBtn = findViewById<Button>(R.id.setBtn)
        val startBtn = findViewById<Button>(R.id.startBtn)


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val selectedItem = spinner.getItemAtPosition(position).toString()

                val pref = getSharedPreferences("pref", MODE_PRIVATE)
                val edit = pref.edit()
                edit.putString("selectedSpinner", selectedItem)
                edit.apply()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //
            }
        }


        setBtn.setOnClickListener {

        }

        startBtn.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
        }

    }

    // initialize spinner
    private val spinnerItem = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    private fun setUpSpinnerItem() {
        val spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter(this, R.layout.spinner_item, spinnerItem)
        spinner.adapter = adapter
    }

}