package com.instagraham.model

import io.ktor.auth.Principal

data class User(val email: String, val name: String, val isHOA: Boolean = false) : Principal {
    var id: Int? = null
}
