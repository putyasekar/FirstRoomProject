package com.putya.idn.firstroomproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_input_word.*

class InputWordActivity : AppCompatActivity() {
    private lateinit var inputWordText: EditText

    companion object {
        const val EXTRA_REPLAY = "com.putya.idn.wordlistsql.REPLAY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_word)

        inputWordText = findViewById(R.id.et_input)

        val button = findViewById<Button>(R.id.btn_save)
        btn_save.setOnClickListener {
            val moveData = Intent()

            if (TextUtils.isEmpty(inputWordText.text)) {
                setResult(Activity.RESULT_CANCELED, moveData)
            } else {
                val word = inputWordText.text.toString()
                moveData.putExtra(
                    EXTRA_REPLAY,
                    word
                ) //dibuat disini dulu, biarin merah nanti dipanggil di companion object EXTRA_REPLAY
                setResult(Activity.RESULT_OK, moveData)
            }
            finish()
        }
    }
}