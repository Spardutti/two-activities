package com.example.twoactivities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SecondActivity : AppCompatActivity() {
    val EXTRA_REPLY = "com.example.android.twoactivities.extra.REPLY"

    var button: Button? = null
    var editText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        editText = findViewById(R.id.editText_secondary)
        button = findViewById(R.id.button_secondary)
        val intent = intent

        val message = intent.getStringExtra("com.example.android.twoactivities.extra.MESSAGE")

        val textView = findViewById<TextView>(R.id.text_message)
        textView.text = message

        button?.setOnClickListener {
            returnReply()
        }
    }

    private fun returnReply() {
        val reply = editText?.text.toString()
        val replyIntent = intent
        replyIntent.putExtra(EXTRA_REPLY, reply)
        setResult(RESULT_OK, replyIntent)
        finish()
    }
}