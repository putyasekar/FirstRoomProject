package com.putya.idn.firstroomproject

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class WordRepo(private val wordDAO: WordDAO) {
    val allWord: LiveData<List<Word>> = wordDAO.getAlphabetWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDAO.insert(word)
    }
}