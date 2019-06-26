package com.instagraham.repository

import com.instagraham.model.User
import java.util.concurrent.atomic.AtomicInteger

class InMemoryRepository : Repository {
    private val idCounter = AtomicInteger()
    private val users = ArrayList<User>()

    override suspend fun add(user: User): User {
        users.find { it == user }?.let {
            return it
        }

        user.id = idCounter.incrementAndGet()
        users.add(user)
        return user
    }

    override suspend fun user(id: Int): User? {
        return users.find { it.id == id }
    }

    override suspend fun userByEmail(email: String): User? {
        return users.find { it.email == email }
    }

    override suspend fun users(): ArrayList<User> {
        return users
    }

    override suspend fun remove(user: User) {
        users.remove(user)
    }

    override suspend fun remove(id: Int) {
        users.removeIf {
            it.id == id
        }
    }

    override suspend fun removeByEmail(email: String) {
        users.removeIf {
            it.email == email
        }
    }

    override suspend fun clear() {
        users.clear()
    }
}