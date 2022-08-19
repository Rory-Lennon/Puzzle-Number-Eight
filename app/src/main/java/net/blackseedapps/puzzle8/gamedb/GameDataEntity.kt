package net.blackseedapps.puzzle8.gamedb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// public class Task implements Serializable {}

@Entity(tableName = "game_data")
data class GameDataEntity(@PrimaryKey val player_id: Int,
                           val highScore01: Int,
                           val highScore02: Int,
                           val highScore03: Int,
                           val gameSpeed: Int,
                           val agreePrivacy: Boolean,
                           val agreeTCs: Boolean,
                           val firstTimeEver: Boolean,
                           val soundVol: Int)
