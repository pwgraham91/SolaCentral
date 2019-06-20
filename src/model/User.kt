package com.instagraham.model

data class User(val email: String, val name: String, val isHOA: Boolean = false) {
    var id: Int? = null
}
