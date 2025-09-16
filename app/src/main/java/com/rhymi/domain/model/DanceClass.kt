package com.rhymi.domain.model

data class DanceClass (
    val id: Int = 0,
    val date: Long?,
    val style: String,
    val teacherName: String,
    val songName: String,
    val videoUrl: String?,
    val notes: String?
)
