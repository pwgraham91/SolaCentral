package com.instagraham.app

import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get

fun Route.about() {
    get("/about") {
        call.respondText("about page")
    }
}