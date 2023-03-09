package com.example.loginwithretrofitinmvvmarchitecture.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginwithretrofitinmvvmarchitecture.data.network.repository.AuthRepository
import com.example.loginwithretrofitinmvvmarchitecture.data.network.repository.BaseRepository
import com.example.loginwithretrofitinmvvmarchitecture.ui.auth.AuthViewModel

class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            else -> throw IllegalArgumentException("ViewModel class not found")
        }
    }//this function will create our viewmodels
}