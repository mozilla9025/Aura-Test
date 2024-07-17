package app.test.aura.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "boot")
data class BootEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val timeStamp: Long,
)