package com.putya.idn.firstroomproject

import android.provider.UserDictionary
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDAO {
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetWords(): LiveData<List<UserDictionary.Words>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: Word)

    @Query("DELETE FROM word_table")
    fun deleteAll()
}