package com.instagraham.app

import com.instagraham.repository.Repository
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get

const val USERS = "/users"

fun Route.users(db: Repository) {
    get(USERS) {
        val users = db.users()
        call.respond(FreeMarkerContent("users.ftl", mapOf("users" to users)))
    }
}