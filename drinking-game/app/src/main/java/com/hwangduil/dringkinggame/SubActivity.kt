package com.hwangduil.dringkinggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val receivedValue = findViewById<TextView>(R.id.receivedValue)
        val pref = getSharedPreferences("pref", MODE_PRIVATE)
        receivedValue.text = pref.getString("selectedSpinner", "")

        // Log.d("selectedSpinner", pref.getString("selectedSpinner", "").toString())

        // 받아온 값
        val rep:Int = pref.getString("selectedSpinner", "")!!.toInt()

        // rep만큼 리니어 레이아웃에 버튼 생성
        val linearLayout = findViewById<LinearLayout>(R.id.btnGroup)
        var childLayout:LinearLayout? = null

        for (i in 0 until rep) {

            // 레이아웃 안에 레이아웃 생성
            if (i % 3 == 0) {
                childLayout = LinearLayout(this)
                childLayout.orientation = LinearLayout.HORIZONTAL
                val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100)
                childLayout.layoutParams = layoutParams
            }

            // 버튼 생성
            val btnParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            btnParams.weight = 1.0f

            val btn = Button(this).apply {
                text = (i + 1).toString()
                layoutParams = btnParams
                id = i
            }

            childLayout?.addView(btn)

            if (i % 3 == 2 || i == (rep - 1)) {
                linearLayout.addView(childLayout)
            }

            // 버튼 클릭 시
            var clickedBtn = false
            btn.apply {
                setOnClickListener {
                    clickedBtn = true
                    isEnabled = false

                    val random = (Math.random() * 10).toInt()
                    text = if (id == random) { "Picked" }
                           else { "Safe" }

                }
            }


        }


        // 이전 페이지로
        val restartBtn = findViewById<Button>(R.id.restartBtn)
        restartBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}
