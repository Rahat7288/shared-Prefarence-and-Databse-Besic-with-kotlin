package com.example.todokotlin.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.todokotlin.utils.Constants

class  DatabaseHandler(val context: Context): SQLiteOpenHelper(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_CONTACTS = "CREATE TABLE ${Constants.TABLE_NAME} (" +
                "${Constants.KEY_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${Constants.KEY_PHONE_NUMBER} TEXT," +
                "${Constants.KEY_NAME} TEXT)"
        db?.execSQL(CREATE_TABLE_CONTACTS)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${Constants.TABLE_NAME}" )
        onCreate(db)
    }

}