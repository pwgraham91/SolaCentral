package com.instagraham

import com.instagraham.api.user
import com.instagraham.app.about
import com.instagraham.app.home
import com.instagraham.app.users
import com.instagraham.model.UserAuth
import com.instagraham.repository.DatabaseFactory
import com.instagraham.repository.UsersRepository
import com.ryanharter.ktor.moshi.moshi
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.basic
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.freemarker.FreeMarker
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respondText
import io.ktor.routing.routing
import com.instagraham.api.users as apiUsers

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(DefaultHeaders)
    install(StatusPages) {
        exception<Throwable> { e ->
            call.respondText(e.localizedMessage, ContentType.Text.Plain, HttpStatusCode.InternalServerError)
        }
    }
    install(ContentNegotiation) {
        moshi()
    }

    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    install(Authentication) {
        basic(name = "auth") {
            realm = "Ktor server"
            validate { credentials ->
                if (credentials.password == "${credentials.name}123") {
                    UserAuth("test@user.com", credentials.name)
                } else {
                    null
                }
            }
        }
    }

    DatabaseFactory.init()

    val db = UsersRepository()

    routing {
        home()
        about()
        users(db)
        user(db)
        apiUsers(db)
    }
}

const val API_VERSION = "/api/v1"
