package com.example.app.viewmodel

import androidx.lifecycle.ViewModel
import com.example.app.global.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    fun getUserId(): Long? = sessionManager.userId

    fun setUserId(id: Long) {
        sessionManager.setUserId(id)
    }

    fun logout() {
        sessionManager.clearSession()
    }
}