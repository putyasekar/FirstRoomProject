package com.putya.idn.firstroomproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val newWordInputRequestCode = 1
    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_main)
        val adapter = WordAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordViewModel.allWords.observe(this, Observer { word ->
            word?.let {
                adapter.setWord(it)
            }
        })
        val fab = findViewById<FloatingActionButton>(R.id.fb_add)
        fab.setOnClickListener {
            val intentToNewActivity = Intent(this, InputWordActivity::class.java)
            startActivityForResult(intentToNewActivity, newWordInputRequestCode)
        }
    }

    //ditambahakn setelah selesai mengerjakan InputWordActivity
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
