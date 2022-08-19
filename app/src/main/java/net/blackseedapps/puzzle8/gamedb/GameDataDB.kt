package net.blackseedapps.puzzle8.gamedb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [GameDataEntity::class], version = 1, exportSchema = false)
abstract class GameDataDB : RoomDatabase() {

    abstract fun gameDataDAO(): GameDataDAO

    companion object {
        @Volatile
        private var INSTANCE: GameDataDB? = null
        private val DATABASE_NAME = "puzzle8_db"

        fun getInstance(context: Context): GameDataDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context.applicationContext).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(appContext: Context): GameDataDB  {
            return Room.databaseBuilder(appContext, GameDataDB::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration() // Data is cache, so it is OK to delete
                .build()
        }
    }
}