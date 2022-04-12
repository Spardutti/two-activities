package com.example.twoactivities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val LOG_TAG = MainActivity::class.java.simpleName

    public val EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE"

    private var mReplyHeadTextView: TextView? = null
    private var mReplyTextView: TextView? = null

    private var messageEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);

        messageEditText = findViewById(R.id.editText_main)

        val button = findViewById<Button>(R.id.button_main)

        button.setOnClickListener {
            launchSecondActivity()
        }
    }

    private fun launchSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        val message = messageEditText?.text.toString()
        intent.putExtra(EXTRA_MESSAGE, message)
        resultLauncher.launch(intent)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                Log.d("reulst", "data")
                // There are no request codes
                val data: Intent? = result.data
                var reply = data?.getStringExtra("com.example.android.twoactivities.extra.REPLY")
                mReplyHeadTextView?.visibility = View.VISIBLE
                mReplyTextView?.text = reply
                mReplyTextView?.visibility = View.VISIBLE
            }
        }
}