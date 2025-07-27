package com.example.rhymi.di

import android.content.Context
import androidx.room.Room
import com.example.rhymi.ClassDatabase
import com.example.rhymi.service.room.ClassDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Set up your Hilt dependency injection graph
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ClassDatabase {
        return Room.databaseBuilder(
            context,
            ClassDatabase::class.java,
            "rhymi_database"
        ).build()
    }

    @Provides
    fun provideClassDao(database: ClassDatabase): ClassDao {
        return database.classDao()
    }
}