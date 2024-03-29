package com.instagraham.app

import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get

const val HOME = "/"

fun Route.home() {
    get(HOME) {
        call.respondText("hello world")
    }
    get("/hello") {
        call.respondText("hello ktor")
    }
}