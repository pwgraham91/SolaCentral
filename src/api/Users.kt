package com.instagraham.api

import com.instagraham.API_VERSION
import com.instagraham.repository.Repository
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get

const val USERS = "$API_VERSION/users"

fun Route.users(db: Repository) {
    get(USERS) {
        val users = db.users()
        call.respond(users.toArray())
    }
}