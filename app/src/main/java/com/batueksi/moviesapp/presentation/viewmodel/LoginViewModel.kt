package com.batueksi.moviesapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batueksi.moviesapp.data.models.auth.User
import com.batueksi.moviesapp.domain.usecase.FirebaseLoginUseCase
import com.batueksi.moviesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: FirebaseLoginUseCase): ViewModel() {


    private val _loginState: MutableLiveData<Resource<User>> = MutableLiveData()
    val loginState: LiveData<Resource<User>>
        get() = _loginState

    fun login(email:String, password:String) {
        viewModelScope.launch {
            loginUseCase(email, password).onEach { state ->
                _loginState.value = state
            }.launchIn(viewModelScope)
        }
    }
}