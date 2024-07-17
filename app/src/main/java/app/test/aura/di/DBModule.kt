package app.test.aura.di

import android.content.Context
import androidx.room.Room
import app.test.aura.data.BootDao
import app.test.aura.data.db.BootDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DBModule {
    @Provides
    fun provideDatabase(@ApplicationContext applicationContext: Context): BootDatabase {
        return Room
            .databaseBuilder(applicationContext, BootDatabase::class.java, "app-boot-db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideBootDao(db: BootDatabase): BootDao {
        return db.bootDao()
    }
}