package com.putya.idn.firstroomproject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(wordApp: Application) : AndroidViewModel(wordApp) {
    private val repository: WordRepo

    val allWords: LiveData<List<Word>>

    init {
        val wordsDao = WordRoomDB.getDB(wordApp, viewModelScope).wordDao()
        repository = WordRepo(wordsDao)
        allWords = repository.allWord
    }

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }
}