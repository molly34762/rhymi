package com.rhymi.service.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "classes")
data class ClassEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: Long?,
    val style: String,
    val teacherName: String,
    val songName: String,
    val videoUrl: String?,
    val notes: String?
)