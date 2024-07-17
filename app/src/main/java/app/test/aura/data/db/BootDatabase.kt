package app.test.aura.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import app.test.aura.data.BootDao
import app.test.aura.data.model.BootEntity


@Database(entities = [BootEntity::class], version = 1, exportSchema = false)
abstract class BootDatabase : RoomDatabase() {

    abstract fun bootDao(): BootDao

}