package com.example.rhymi.service.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "videos",
    foreignKeys = [ForeignKey(
        entity = ClassEntity::class,
        parentColumns = ["id"],
        childColumns = ["danceClassId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["danceClassId"])]
)
data class VideoEntity (
    @PrimaryKey val id: Int,
    val danceClassId: Int,
    val videoUrl: String
)