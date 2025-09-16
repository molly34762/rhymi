package com.rhymi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rhymi.domain.model.DanceClass
import com.rhymi.service.repository.DanceClassRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddClassViewModel @Inject constructor(
    private val danceClassRepository: DanceClassRepository
): ViewModel() {

    fun saveDanceClass(danceClass: DanceClass) {
        viewModelScope.launch {
            danceClassRepository.insertClass(danceClass)
        }
    }
}