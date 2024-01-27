package com.example.todokotlin.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.Contacts
import com.example.todokotlin.model.Contact
import com.example.todokotlin.utils.Constants

class  DatabaseHandler(val context: Context): SQLiteOpenHelper(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_CONTACTS = "CREATE TABLE ${Constants.TABLE_NAME} (" +
                "${Constants.KEY_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${Constants.KEY_NAME} TEXT," +
                "${Constants.KEY_PHONE_NUMBER} TEXT)"
        db?.execSQL(CREATE_TABLE_CONTACTS)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${Constants.TABLE_NAME}" )
        onCreate(db)
    }

/*
* CRUD Operations ================
* */

    fun addContact(contact: Contact){
        val db = this.writableDatabase

        val values = ContentValues()

        values.put(Constants.KEY_NAME, contact.name)
        values.put(Constants.KEY_PHONE_NUMBER, contact.phoneNumber)
//        insert to row==========
        db.insert(Constants.TABLE_NAME, null,values)
//        we need to close the database =========
        db.close()
    }


//    get individual contact
    @SuppressLint("Recycle")
    fun getContact(id: Int): Contact{
        val db = this.readableDatabase
        val cursor = db.query(Constants.TABLE_NAME,
            arrayOf(Constants.KEY_ID, Constants.TABLE_NAME, Constants.KEY_PHONE_NUMBER),
            Constants.KEY_ID + "=?", arrayOf(id.toString()), null, null, null, null
            )
        cursor?.moveToFirst()

        return  Contact(cursor.getString(0).toInt(),cursor.getString(1), cursor.getString(2))
    }

//    get all contacts =======

    @SuppressLint("Recycle")
    fun getAllContacts(): MutableList<Contact>{
        val db = this.readableDatabase

        val contactList = mutableListOf<Contact>()

        val selectAll = "SELECT * FROM ${Constants.TABLE_NAME}"
        val  cursor = db.rawQuery(selectAll, null)
        if(cursor.moveToFirst()){
            do {
                val contact = Contact(0, "" , "")
                contact.id = cursor.getString(0).toString().toInt()
                contact.name = cursor.getString(1)
                contact.phoneNumber = cursor.getString(2)

// add contact to the contact list
                contactList.add(contact)
            } while(cursor.moveToNext())
        }
        return  contactList
    }





}