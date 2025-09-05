package com.rhymi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rhymi.service.repository.DanceClassRepository
import com.rhymi.service.room.ClassEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddClassViewModel @Inject constructor(
    private val danceClassRepository: DanceClassRepository
): ViewModel() {

    fun saveDanceClass(classEntity: ClassEntity) {
        viewModelScope.launch {
            danceClassRepository.insertClass(classEntity)
        }
    }
}