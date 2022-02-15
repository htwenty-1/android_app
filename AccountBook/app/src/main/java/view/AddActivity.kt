package view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.hwangduil.accountbook.MainActivity
import com.hwangduil.accountbook.R
import com.hwangduil.accountbook.databinding.ActivityAddBinding
import dbHelper.DBHelper
import dto.AccountDto
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    // dbHelper 초기화
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = DBHelper(this, "account_book.db")

        // 라디오 버튼 클릭 시 값 담아주기
        var category:String? = ""
        binding.wrapClassify.setOnCheckedChangeListener { _, checked ->
            when(checked) {
                R.id.addExpense -> category = binding.addExpense.text.toString()
                R.id.addIncome -> category = binding.addIncome.text.toString()
            }
            Log.d("value", category!!)
        }

        // 추가 버튼 클릭시 처리 내용
        binding.addInsertBtn.setOnClickListener{

            // 제목(용도)
            val title = binding.addTitle.text.toString()

            // datePicker 날짜 얻어오기
            val parse = DateTimeFormatter.ofPattern("yyyy-MM-dd")

            val dateTime = if(binding.datePickerOfAdd.month+1 < 10) {
                                if(binding.datePickerOfAdd.dayOfMonth < 10) {
                                    "${binding.datePickerOfAdd.year}-0${binding.datePickerOfAdd.month+1}-0${binding.datePickerOfAdd.dayOfMonth}"
                                } else {
                                    "${binding.datePickerOfAdd.year}-0${binding.datePickerOfAdd.month+1}-${binding.datePickerOfAdd.dayOfMonth}"
                                }
                            } else {
                                if(binding.datePickerOfAdd.dayOfMonth < 10) {
                                    "${binding.datePickerOfAdd.year}-${binding.datePickerOfAdd.month+1}-0${binding.datePickerOfAdd.dayOfMonth}"
                                } else {
                                    "${binding.datePickerOfAdd.year}-${binding.datePickerOfAdd.month+1}-${binding.datePickerOfAdd.dayOfMonth}"
                                }
                            }

            val date = LocalDate.parse(dateTime, parse).toString()

            Log.d("date", date)

            // 금액
            val amount:Int = binding.addAmount.text.toString().toInt()

            // 메모
            val memo = binding.addMemo.text.toString()

            // dbHelper의 insert 메서드에 넣어줄 dto 선언, 초기화
            val dto = AccountDto(category!!, title, date, amount, memo)

            dbHelper.insert(dto)

            // 추가되었음을 알려주는 Toast 띄우기
            Toast.makeText(this, "${title}이(가) 추가 되었습니다.", Toast.LENGTH_SHORT).show()

        }

        // '메뉴로' 버튼 클릭시 메인 화면으로
        binding.addBackBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}