package com.rhymi.viewmodel

import androidx.lifecycle.ViewModel
import com.rhymi.service.repository.DanceClassRepository
import com.rhymi.service.room.ClassEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    danceClassRepository: DanceClassRepository
) : ViewModel() {

    val classes: Flow<List<ClassEntity>> = danceClassRepository.getAllClasses()
}