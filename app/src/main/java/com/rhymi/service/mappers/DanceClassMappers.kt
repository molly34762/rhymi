package com.rhymi.service.mappers

import com.rhymi.domain.model.DanceClass
import com.rhymi.domain.model.Video
import com.rhymi.service.room.ClassEntity
import com.rhymi.service.room.VideoEntity

fun DanceClass.toEntity() = ClassEntity(
    id = id,
    date = date,
    style = style,
    teacherName = teacherName,
    songName = songName,
    videoUrl = null,
    notes = notes
)

fun ClassEntity.toDomain() = DanceClass(
    id = id,
    date = date,
    style = style,
    teacherName = teacherName,
    songName = songName,
    videoUrl = null,
    notes = notes
)

fun VideoEntity.toDomain() = Video(
    id = id,
    danceClassId = danceClassId,
    videoUrl = videoUrl
)