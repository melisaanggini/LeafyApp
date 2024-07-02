package com.example.leafy.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.leafy.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByUsername(username: String): User?
}
