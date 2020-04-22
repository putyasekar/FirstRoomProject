package com.putya.idn.firstroomproject

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDB : RoomDatabase() {
}