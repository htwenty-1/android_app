package view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hwangduil.accountbook.MainActivity
import com.hwangduil.accountbook.R
import com.hwangduil.accountbook.databinding.ActivitySearchPeriodBinding
import dbHelper.DBHelper
import view.recycler.CustomAdapter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SearchPeriodActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding:ActivitySearchPeriodBinding

    private val dbHelper = DBHelper(this, "account_book.db")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchPeriodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchPeriBackBtn.setOnClickListener(this)
        binding.searchPeriBtn.setOnClickListener(this)
    }

    @SuppressLint("ResourceType")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(view: View?) {
        when(view) {
            binding.searchPeriBackBtn -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            binding.searchPeriBtn -> {

                val parse = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val startDateEx = if(binding.startDate.month+1 < 10) {
                    if(binding.startDate.dayOfMonth < 10) {
                        "${binding.startDate.year}-0${binding.startDate.month+1}-0${binding.startDate.dayOfMonth}"
                    } else {
                        "${binding.startDate.year}-0${binding.startDate.month+1}-${binding.startDate.dayOfMonth}"
                    }
                } else {
                    if(binding.startDate.dayOfMonth < 10) {
                        "${binding.startDate.year}-${binding.startDate.month+1}-0${binding.startDate.dayOfMonth}"
                    } else {
                        "${binding.startDate.year}-${binding.startDate.month+1}-${binding.startDate.dayOfMonth}"
                    }
                }
                val endDateEx = if(binding.endDate.month+1 < 10) {
                    if(binding.endDate.dayOfMonth < 10) {
                        "${binding.endDate.year}-0${binding.endDate.month+1}-0${binding.endDate.dayOfMonth}"
                    } else {
                        "${binding.endDate.year}-0${binding.endDate.month+1}-${binding.endDate.dayOfMonth}"
                    }
                } else {
                    if(binding.endDate.dayOfMonth < 10) {
                        "${binding.endDate.year}-${binding.endDate.month+1}-0${binding.endDate.dayOfMonth}"
                    } else {
                        "${binding.endDate.year}-${binding.endDate.month+1}-${binding.endDate.dayOfMonth}"
                    }
                }

                val startDate = LocalDate.parse(startDateEx, parse).toString()
                val endDate = LocalDate.parse(endDateEx, parse).toString()


//                val textView = TextView(this).apply {
//                    text = dbHelper.selectWithPeriod(startDate, endDate)
//                }
//
//                binding.searchPeriodLayout.addView(textView)

                val list = dbHelper.searchWithPeriod(startDate, endDate)

                val mAdapter = CustomAdapter(this, list)
                binding.searchPeriodRecyclerView.adapter = mAdapter

                val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.searchPeriodRecyclerView.layoutManager = layout
                binding.searchPeriodRecyclerView.setHasFixedSize(true)

//                for (i in list.indices) {
//                    val index = findViewById<View>(R.id.recyclerIndexText) as TextView?
//                    index?.text = i.toString()
//                }


                // 리사이클러뷰에 번호 뿌려주기(보완 필요..)
                var i = 0
                while(i < list.size) {
                    val index = findViewById<View>(R.id.recyclerIndexText) as TextView?
                    index?.text = i.toString()
                    i++
                }


            }
        }
    }
}