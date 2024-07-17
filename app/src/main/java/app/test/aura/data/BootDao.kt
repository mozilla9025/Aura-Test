package app.test.aura.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.test.aura.data.model.BootEntity

@Dao
interface BootDao {
    @Insert(entity = BootEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bootEntity: BootEntity)

    @Query("SELECT * FROM boot")
    suspend fun getAll(): List<BootEntity>
}