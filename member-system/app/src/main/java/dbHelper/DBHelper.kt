package dbHelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?,
                    name: String?,
                    factory: CursorFactory?,
                    version: Int)
      : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        val sql: String = " CREATE TABLE IF NOT EXISTS MEMBER( " +
                          " SEQ INTEGER PRIMARY KEY AUTOINCREMENT, " +
                          " MEMBER_NAME VARCHAR, " +
                          " MEMBER_AGE VARCHAR, " +
                          " MEMBER_ADDRESS TEXT) "
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val sql: String = " DROP TABLE IF EXISTS MEMBER "
        db?.execSQL(sql)
        onCreate(db)
    }

    // CRUD
    fun insert(db:SQLiteDatabase, memberName:String, memberAge: String, memberAddress:String) {
        val sql: String = " INSERT INTO MEMBER(MEMBER_NAME, MEMBER_AGE, MEMBER_ADDRESS) " +
                          " VALUES('${memberName}', '${memberAge}', '${memberAddress}') "
        db.execSQL(sql)
    }

    fun delete(db:SQLiteDatabase, memberName:String) {
        val sql: String = " DELETE FROM MEMBER " +
                          " WHERE MEMBER_NAME = '${memberName}' "
        db.execSQL(sql)
    }

    fun search(db:SQLiteDatabase, memberName: String) {
        val sql: String = " SELECT * FROM MEMBER " +
                          " WHERE MEMBER_NAME = '${memberName}' "
        db.execSQL(sql)
    }

    fun update(db:SQLiteDatabase, memberName: String, memberAge: Int, memberAddress: String) {
        val sql: String = " UPDATE MEMBER " +
                          " SET MEMBER_AGE = $memberAge " +
                          " SET MEMBER_ADDRESS = '$memberAddress' " +
                          " WHERE MEMBER_NAME = '${memberName}' "
        db.execSQL(sql)
    }

    fun allMember(db: SQLiteDatabase) {
        val sql: String = " SELECT * FROM MEMBER "
        db.execSQL(sql)
    }


    companion object {
        private var dbHelper: DBHelper? = null
        fun getInstance(context: Context?, name:String?, factory: CursorFactory?, version: Int): DBHelper {
            if (dbHelper == null) {
                dbHelper = DBHelper(context, name, factory, version)
            }
            return dbHelper!!
        }
    }



}