package com.putya.idn.firstroomproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.putya.idn.firstroomproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val newWordInputRequestCode = 1
    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("UNUSED_VARIABLE")
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val recyclerView = binding.rvMain
        val adapterWord = WordAdapter()

        recyclerView.adapter = adapterWord
        recyclerView.layoutManager = LinearLayoutManager(this)

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        wordViewModel.allWords.observe(this, Observer { word ->
            word?.let {
                adapterWord.setWord(it)
            }
        })

        val fab = binding.fbAdd
        fab.setOnClickListener {
            val intentToNewActivity = Intent(this, InputWordActivity::class.java)
            startActivityForResult(intentToNewActivity, newWordInputRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        if (requestCode == newWordInputRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val word = Word(data.getStringExtra(InputWordActivity.EXTRA_REPLAY))
                wordViewModel.insert(word)
                Unit
            }

        } else {
            Toast.makeText(applicationContext, getString(R.string.empty), Toast.LENGTH_LONG).show()
        }
    }
}