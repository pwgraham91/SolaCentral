package com.instagraham.api

import com.instagraham.API_VERSION
import com.instagraham.model.Request
import com.instagraham.repository.Repository
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post

const val USER_ENDPOINT = "$API_VERSION/user"

fun Route.user(db: Repository) {
    authenticate("auth") {
        post(USER_ENDPOINT) {
            val request = call.receive<Request>()
            val user = db.add(request.email, request.name)
            call.respond(user)
        }
    }
}
