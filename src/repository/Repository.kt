package com.instagraham.repository

import com.instagraham.model.User


interface Repository {
    suspend fun add(email: String, name: String, isHOA: Boolean = false)
    suspend fun user(id: Int): User?
    suspend fun users(): List<User>
    suspend fun remove(id: Int)
    suspend fun clear()
}