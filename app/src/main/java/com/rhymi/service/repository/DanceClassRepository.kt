package com.rhymi.service.repository

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

    suspend fun insertClass(classEntity: ClassEntity) {
        classDao.insertClass(classEntity)
    }
}