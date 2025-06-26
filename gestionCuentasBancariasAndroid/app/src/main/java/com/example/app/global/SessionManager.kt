package com.example.app.global

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    var userId: Long? = null
        private set

    fun setUserId(id: Long) {
        userId = id
    }

    fun clearSession() {
        userId = null
    }
}