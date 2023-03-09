package com.example.loginwithretrofitinmvvmarchitecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.example.loginwithretrofitinmvvmarchitecture.data.network.AuthApi
import com.example.loginwithretrofitinmvvmarchitecture.data.network.Resource
import com.example.loginwithretrofitinmvvmarchitecture.data.network.repository.AuthRepository
import com.example.loginwithretrofitinmvvmarchitecture.databinding.FragmentLoginBinding
import com.example.loginwithretrofitinmvvmarchitecture.ui.auth.AuthViewModel
import com.example.loginwithretrofitinmvvmarchitecture.ui.base.BaseFragment
import com.example.loginwithretrofitinmvvmarchitecture.ui.enable
import com.example.loginwithretrofitinmvvmarchitecture.ui.home.HomeActivity
import com.example.loginwithretrofitinmvvmarchitecture.ui.startNewActivity
import com.example.loginwithretrofitinmvvmarchitecture.ui.visible


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressbar.visible(false)
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(false)
            binding.buttonLogin.enable(false)
            when(it){
                is Resource.Success -> {

                    viewModel.saveAuthToken(it.value.cnicNumber.toString())
                        requireActivity().startNewActivity(HomeActivity::class.java)

                }

                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_SHORT).show()
                }
            }

        })//as to observe the loginresponse live data

        binding.editTextTextPassword.addTextChangedListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            binding.buttonLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()
            binding.progressbar.visible(true)
            viewModel.login(email, password)
        }
    }
    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)


}

