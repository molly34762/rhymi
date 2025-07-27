package com.example.rhymi.viewmodel

import androidx.lifecycle.ViewModel
import com.example.rhymi.service.repository.DanceClassRepository
import com.example.rhymi.service.room.ClassEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val danceClassRepository: DanceClassRepository
) : ViewModel() {

    fun getAllClasses(): Flow<List<ClassEntity>> {
        return danceClassRepository.getAllClasses()
    }

    suspend fun insertClass(classEntity: ClassEntity) {
        danceClassRepository.insertClass(classEntity)
    }

    suspend fun deleteClass(classEntity: ClassEntity) {
        danceClassRepository.deleteClass(classEntity)
    }

    suspend fun getClassById(id: Int): ClassEntity? {
        return danceClassRepository.getClassById(id)
    }
}