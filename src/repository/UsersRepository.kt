package com.instagraham.repository

import com.instagraham.model.User
import com.instagraham.model.Users
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class UsersRepository : Repository {
    override suspend fun add(email: String, name: String, isHOA: Boolean) {
        transaction {
            Users.insert {
                it[this.email] = email
                it[this.name] = name
                it[this.isHOA] = isHOA
            }
        }
    }

    override suspend fun user(id: Int): User? = DatabaseFactory.dbQuery {
        Users.select {
            (Users.id eq id)
        }.mapNotNull { toUser(it) }.singleOrNull()
    }

    override suspend fun users(): List<User> = DatabaseFactory.dbQuery { Users.selectAll().map { toUser(it) } }

    override suspend fun remove(id: Int) {
        if (user(id) == null) {
            throw IllegalArgumentException("No user found with id $id")
        }
        return DatabaseFactory.dbQuery {
            Users.deleteWhere {
                Users.id eq id
            }
        }
    }

    override suspend fun clear() {
        DatabaseFactory.dbQuery {
            Users.deleteAll()
        }
    }

    private fun toUser(row: ResultRow): User =
        User(id = row[Users.id].value, email = row[Users.email], name = row[Users.name], isHOA = row[Users.isHOA])
}