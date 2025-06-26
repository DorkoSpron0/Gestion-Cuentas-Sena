package com.example.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.global.SessionManager
import com.example.app.model.api.UserModelApi
import com.example.app.repository.UserRepositoryApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepositoryApi,
    private val sessionManager: SessionManager
) : ViewModel(){

    private val _user = MutableStateFlow<Response<UserModelApi>?>(null)
    val user: StateFlow<Response<UserModelApi>?> = _user.asStateFlow()

    private val _userLogged = MutableStateFlow<Response<UserModelApi>?>(null)
    val userLogged: StateFlow<Response<UserModelApi>?> = _userLogged.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                val newUser = UserModelApi(
                    email = email,
                    password = password
                )
                val response = repository.registerUser(newUser)
                if (response.isSuccessful) {
                    _user.value = response
                } else {
                    _error.value = "Error: ${response.code()} - ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Excepción: ${e.localizedMessage ?: "Error desconocido"}"
            }
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                val newUser = UserModelApi(
                    email = email,
                    password = password
                )
                val response = repository.loginUser(newUser)
                if (response.isSuccessful) {
                    _userLogged.value = response
                    sessionManager.setUserId(_userLogged.value!!.body()?.id!!)
                } else {
                    _error.value = "Error: ${response.code()} - ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Excepción: ${e.localizedMessage ?: "Error desconocido"}"
            }
        }
    }
}