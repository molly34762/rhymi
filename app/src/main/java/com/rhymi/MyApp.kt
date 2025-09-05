package com.rhymi

import android.app.Application
import androidx.room.Database
import androidx.room.RoomDatabase
import com.rhymi.service.room.ClassDao
import com.rhymi.service.room.ClassEntity
import com.rhymi.service.room.VideoEntity
import dagger.hilt.android.HiltAndroidApp

@Database(entities = [ClassEntity::class, VideoEntity::class], version = 1, exportSchema = false)
abstract class ClassDatabase : RoomDatabase() {
    abstract fun classDao(): ClassDao
}

@HiltAndroidApp
class MyApp : Application()
