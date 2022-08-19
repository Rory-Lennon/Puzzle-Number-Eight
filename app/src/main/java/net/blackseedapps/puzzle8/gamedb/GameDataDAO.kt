package net.blackseedapps.puzzle8.gamedb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import net.blackseedapps.puzzle8.gamedb.*

@Dao
interface GameDataDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHS(hsEntity : GameDataEntity)

    @Query("select * FROM game_data")
    fun readHS() : List<GameDataEntity>
}