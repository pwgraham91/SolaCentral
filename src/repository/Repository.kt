package com.instagraham.repository

import com.instagraham.model.User


interface Repository {
    suspend fun add(user: User): User
    suspend fun user(id: Int): User?
    suspend fun userByEmail(email: String): User?
    suspend fun users(): List<User>
    suspend fun remove(user: User)
    suspend fun remove(id: Int)
    suspend fun removeByEmail(email: String)
    suspend fun clear()
}