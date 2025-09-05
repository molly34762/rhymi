package com.rhymi.service.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassDao {

    // Get all classes
    @Query("SELECT * FROM classes")
    fun getAllClasses(): Flow<List<ClassEntity>>

    // Insert a class
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClass(classEntity: ClassEntity)

    // Delete a class
    @Delete
    suspend fun deleteClass(classEntity: ClassEntity)

    // Get a class by id
    @Query("SELECT * FROM classes WHERE id = :id")
    suspend fun getClassById(id: Int) : ClassEntity?
}
