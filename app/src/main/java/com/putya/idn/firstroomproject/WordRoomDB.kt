package com.putya.idn.firstroomproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDB : RoomDatabase() {
    abstract fun wordDao(): WordDAO

    companion object {

        @Volatile
        private var INSTANCE: WordRoomDB? = null

        fun getDB(context: Context, scope: CoroutineScope): WordRoomDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, WordRoomDB::class.java, "word_db"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDBCallBack(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class WordDBCallBack(private val scope: CoroutineScope) :
        RoomDatabase.Callback() { //coroutine - dagger - default courotine
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    accessDatabase(database.wordDao())
                }
            }
        }

        private fun accessDatabase(wordDao: WordDAO) {
            var word = Word("Hi Putya Have a Nice Day!")
            wordDao.insert(word)

            word = Word("Halo Putya!")
            wordDao.insert(word)

            wordDao.deleteAll()
        }
    }
}