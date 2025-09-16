package com.rhymi.service.repository

import com.rhymi.domain.model.DanceClass
import com.rhymi.service.mappers.toEntity
import com.rhymi.service.room.ClassDao
import com.rhymi.service.room.ClassEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class DanceClassRepository @Inject constructor (
    private val classDao: ClassDao
) {

    fun getAllClasses(): Flow<List<ClassEntity>> {
        return classDao.getAllClasses()
    }

    suspend fun insertClass(danceClass: DanceClass) {
        classDao.insertClass(danceClass.toEntity())
    }
}