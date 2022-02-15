package dbHelper

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dto.AccountDto

class DBHelper(context: Context, fileName: String) : SQLiteOpenHelper(context, fileName, null, 1) {

    companion object {
        private var dbHelper: DBHelper? = null
        fun getInstance(context: Context, fileName: String): DBHelper? {
            if(dbHelper != null) {
                dbHelper = DBHelper(context, fileName)
            }

            return dbHelper!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = " CREATE TABLE IF NOT EXISTS ACCOUNTS( " +
                    "   SEQ INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "   CLASSIFY VARCHAR, " +
                    "   TITLE VARCHAR, " +
                    "   ADD_DATE DATE, " +
                    "   AMOUNTS INTEGER, " +
                    "   MEMO VARCHAR) "
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, newVer: Int, oldVer: Int) {
        val query = " DROP TABLE IF EXISTS ACCOUNTS "
        db?.execSQL(query)
        onCreate(db)
    }

    // CRUD
    fun insert(dto:AccountDto) {
        val query = " INSERT INTO ACCOUNTS (CLASSIFY, TITLE, ADD_DATE, AMOUNTS, MEMO ) " +
                    "   VALUES ('${dto.classify}', '${dto.title}', date('${dto.date}'), ${dto.amount}, '${dto.memo}') "
        val db = this.writableDatabase
        db?.execSQL(query)
    }

    @SuppressLint("Recycle", "Range")
    fun select(data:String) : String?{
        val query = " SELECT * FROM ACCOUNTS " +
                    "   WHERE CLASSIFY ='${data}' OR TITLE ='${data}' OR MEMO ='${data}' "
        val db = this.writableDatabase
        val res = db.rawQuery(query, null)

        var str:String? = ""
        while(res.moveToNext()){
            str += "구분 : " + res.getString(res.getColumnIndex("CLASSIFY")) +
                   "\n제목 : " + res.getString(res.getColumnIndex("TITLE")) +
                   "\n날짜 : " + res.getString(res.getColumnIndex("ADD_DATE")) +
                   "\n금액 : " + res.getString(res.getColumnIndex("AMOUNTS")) +
                   "\n메모 : " + res.getString(res.getColumnIndex("MEMO")) +
                   "\n\n"
        }

        return str
    }

    @SuppressLint("Range", "Recycle")
    fun selectWithPeriod(startDate:String, endDate:String) : String? {
        val query = " SELECT * FROM ACCOUNTS " +
                    "   WHERE ADD_DATE BETWEEN ('${startDate}') AND ('${endDate}') "

        val db = this.writableDatabase
        val res = db.rawQuery(query, null)

        var str:String? = ""
        while(res.moveToNext()) {
            str += "구분 : " + res.getString(res.getColumnIndex("CLASSIFY")) +
                   "\n제목 : " + res.getString(res.getColumnIndex("TITLE")) +
                   "\n날짜 : " + res.getString(res.getColumnIndex("ADD_DATE")) +
                   "\n금액 : " + res.getString(res.getColumnIndex("AMOUNTS")) +
                   "\n메모 : " + res.getString(res.getColumnIndex("MEMO")) +
                   "\n\n"
        }

        return str
    }

    @SuppressLint("Range", "Recycle")
    fun searchWithPeriod(startDate: String, endDate: String): ArrayList<AccountDto> {
        val query = " SELECT * FROM ACCOUNTS " +
                "   WHERE ADD_DATE BETWEEN ('${startDate}') AND ('${endDate}') "

        val db = this.writableDatabase
        val res = db.rawQuery(query, null)

        val dto = AccountDto("", "", "", 0, "")
        var list = arrayListOf<AccountDto>()
        while (res.moveToNext()) {
//            dto.classify = res.getString(res.getColumnIndex("CLASSIFY"))
//            dto.title = res.getString(res.getColumnIndex("TITLE"))
//            dto.date = res.getString(res.getColumnIndex("ADD_DATE"))
//            dto.amount = res.getInt(res.getColumnIndex("AMOUNTS"))
//            dto.memo = res.getString(res.getColumnIndex("MEMO"))
            list.add(AccountDto(
                res.getString(res.getColumnIndex("CLASSIFY")),
                res.getString(res.getColumnIndex("TITLE")),
                res.getString(res.getColumnIndex("ADD_DATE")),
                res.getInt(res.getColumnIndex("AMOUNTS")),
                res.getString(res.getColumnIndex("MEMO"))
            ))
        }

        return list
    }
}