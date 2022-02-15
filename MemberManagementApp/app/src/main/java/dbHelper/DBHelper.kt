package dbHelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import vo.Member

class DBHelper(context: Context, name: String?) : SQLiteOpenHelper(context, name, null, 1) {

    companion object {
        private var dbHelper: DBHelper? = null
        fun getInstance(context: Context, name: String?): DBHelper? {
            if(dbHelper != null) {
                dbHelper = DBHelper(context, name)
            }

            return dbHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = " CREATE TABLE IF NOT EXISTS MEMBERS( " +
                    "     SEQ INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "     MEMBER_NAME VARCHAR, " +
                    "     MEMBER_AGE INTEGER, " +
                    "     MEMBER_LOCATION VARCHAR) "

        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, old: Int, new: Int) {
        val query = " DROP TABLE IF EXISTS MEMBER "
        db?.execSQL(query)
        onCreate(db)
    }

    // CRUD
    fun insert(vo:Member) {
        val query = " INSERT INTO (MEMBER_NAME, MEMBER_AGE, MEMBER_LOCATION) " +
                    " VALUES ('${vo.name}', ${vo.age}, '${vo.location}') "
        val db = dbHelper?.writableDatabase
        db?.execSQL(query)
    }


    // 조건에 따른 검색
    fun search(vo:Member) {}

    fun edit() {}

    fun delete(name:String, index:Int) {
        val query = " DELETE FROM MEMBERS " +
                    " WHERE MEMBER_NAME = '${name}' AND SEQ = $index "
        val db = dbHelper?.writableDatabase
        db?.execSQL(query)
    }

    // 모든 회원 출력
    fun allPrint() {
        val query = " SELECT * FROM MEMBERS "
        val db = dbHelper?.readableDatabase
        db?.execSQL(query)
    }

}