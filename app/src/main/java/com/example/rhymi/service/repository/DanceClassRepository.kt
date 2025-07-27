package com.example.rhymi.service.repository

import com.example.rhymi.service.room.ClassDao
import com.example.rhymi.service.room.ClassEntity
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

    suspend fun deleteClass(classEntity: ClassEntity) {
        classDao.deleteClass(classEntity)
    }

    suspend fun getClassById(id: Int): ClassEntity? {
        return classDao.getClassById(id)
    }
}