package com.example.loginwithretrofitinmvvmarchitecture.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginwithretrofitinmvvmarchitecture.data.network.Resource
import com.example.loginwithretrofitinmvvmarchitecture.data.network.repository.AuthRepository
import com.example.loginwithretrofitinmvvmarchitecture.data.network.responses.LoginResponse
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository//with the help of this repository instance we can call the login function which is present in the auth repository
) : ViewModel() {

    private val _loginResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()//making login response as mutable live data as to access it inside the auth view model because if it is not made mutable so we can not change it inside the auth view model.
    val loginResponse : LiveData<Resource<LoginResponse>>//creating live data as to store the login response and this is immutable.
    get() = _loginResponse//we will receive mutable live data as live data.

    fun login(
        email : String,
        password : String
    )//this function will hit the login api with the help of repository

           = viewModelScope.launch{
        _loginResponse.value = repository.login(email, password)

    }//using viewmodelscope.launch as we are calling the function of auth repository which is coroutine suspending function
    fun saveAuthToken(token : String) = viewModelScope.launch{
        repository.saveAuthToken(token)
    }
}