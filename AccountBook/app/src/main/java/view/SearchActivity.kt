package view

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.hwangduil.accountbook.MainActivity
import com.hwangduil.accountbook.R
import com.hwangduil.accountbook.databinding.ActivitySearchBinding
import dbHelper.DBHelper

class SearchActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySearchBinding
    private val dbHelper = DBHelper(this, "account_book.db")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSpinner()

        binding.searchBackBtn.setOnClickListener(this)
        binding.searchMenuBtn.setOnClickListener(this)
    }

    // 스피너 초기화
    fun initSpinner() {
        val items = arrayOf("옵션선택", "카테고리", "제목", "메모")
        val adapter = ArrayAdapter(this, R.layout.item_spinner, items)

        val spinner = binding.searchSpinner
        spinner.adapter = adapter
    }

    var spinnerValue = ""
    @SuppressLint("SetTextI18n")
    override fun onClick(view: View?) {

        when(view) {
            binding.searchBackBtn -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            binding.searchMenuBtn -> {
                // 스피너 선택 내용
                binding.searchSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                        spinnerValue = binding.searchSpinner.getItemAtPosition(pos).toString()
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        //
                    }
                }

                Log.d("val", spinnerValue)


                // DBHelper의 search 함수 사용, 가져온 정보를 LinearLayout에 뿌리기
                if(spinnerValue == "카테고리" || spinnerValue == "제목" || spinnerValue == "메모") {

                    val textView = TextView(this).apply {
                        text = dbHelper.select(binding.searchKeyword.text.toString())
                    }

                    binding.searchViewLayout.addView(textView)

                } else {
                    AlertDialog.Builder(this@SearchActivity)
                        .setTitle("잘못된 검색 옵션 선택")
                        .setMessage("잘못된 옵션이 선택되었습니다. 다시 선택해주세요.")
                        .setCancelable(false)
                        .setNeutralButton("닫기", DialogInterface.OnClickListener{
                                _, _ ->
                        }).show()
                }

            }
        }
    }
}