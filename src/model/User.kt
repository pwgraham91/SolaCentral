package com.instagraham.model

import io.ktor.auth.Principal
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import java.io.Serializable

data class UserAuth(val email: String, val name: String, val isHOA: Boolean = false) : Principal {
    var id: Int? = null
}

data class User(
    val id: Int,
    val email: String,
    val name: String,
    val isHOA: Boolean = false
) : Serializable, Principal

object Users : IntIdTable() {
    val email: Column<String> = varchar("email", 255)
    val name: Column<String> = varchar("name", 255)
    val isHOA: Column<Boolean> = bool("isHOA")
}