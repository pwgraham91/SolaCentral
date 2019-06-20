package com.instagraham

import com.instagraham.app.about
import com.instagraham.app.home
import io.ktor.application.Application
import io.ktor.routing.routing

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    routing {
        home()
        about()
    }
}

