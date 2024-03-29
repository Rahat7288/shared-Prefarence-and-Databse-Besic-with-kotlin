package com.example.todokotlin

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.todokotlin.data.DatabaseHandler
import com.example.todokotlin.model.Contact

class MainActivity : AppCompatActivity() {
//    private lateinit var editTextMessage: EditText
//    private lateinit var saveButton: Button
//    private lateinit var textViewMessage: TextView
////    declaring sharedPreference================
//    private  val PREF_NAME = "myPref"
//    private  lateinit var sharePref: SharedPreferences

    private lateinit var textViewData: TextView
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        editTextMessage = findViewById(R.id.edit_text_message)
//        saveButton = findViewById(R.id.save_button)
//        textViewMessage = findViewById(R.id.text_view_message)
//
//        saveButton.setOnClickListener {
////            mode = 0 means that is access able
//            sharePref = getSharedPreferences(PREF_NAME, 0)
//            val editor = sharePref.edit()
////key pare the key is hare is message
//            editor.putString("message",editTextMessage.text.toString())
//            editor.apply()
//
//        }
//
//        val prefs = getSharedPreferences(PREF_NAME, 0)
////        if the message isn't empty then we can get the value
////        or we will see not found
//        if(prefs.contains("message")){
//            val message = prefs.getString("message", "not found")
//            textViewMessage.text = message
//        }

//    shared preference section done ==================================
        textViewData = findViewById(R.id.text_view_data)

    val db = DatabaseHandler(this)

    db.addContact(Contact("Rahat", "3294829038"))
    db.addContact(Contact("Akash", "3478207342"))
    db.addContact(Contact("Asad", "3242848"))
    db.addContact(Contact("Niloy", "23845902"))

        val contactList = db.getAllContacts()

        for (contact in contactList){
            textViewData.append("\nName: ${contact.name}"+"'\nPhoneNumber: ${contact.phoneNumber}")
        }


    }
}