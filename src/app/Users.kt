package com.instagraham.app

import com.instagraham.model.User
import com.instagraham.repository.Repository
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get

const val USERS = "/users"

fun Route.users(db: Repository) {
    authenticate("auth") {
        get(USERS) {
            val users = db.users()
            val authenticatedUser = call.authentication.principal as User

            call.respond(
                FreeMarkerContent(
                    "users.ftl",
                    mapOf(
                        "users" to users,
                        "authenticatedUserName" to authenticatedUser.name
                    )
                )
            )
        }
    }
}