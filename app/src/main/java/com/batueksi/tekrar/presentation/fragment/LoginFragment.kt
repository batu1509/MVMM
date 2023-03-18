package com.batueksi.tekrar.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.batueksi.tekrar.R
import com.batueksi.tekrar.databinding.FragmentLoginBinding
import com.batueksi.tekrar.presentation.viewmodel.LoginViewModel
import com.batueksi.tekrar.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initListeners()
    }

    private fun initObservers(){
        viewModel.loginState.observe(viewLifecycleOwner){state->
            when(state){
                is Resource.Success -> {
                    handleLoading(isLoading = false)
                    findNavController().navigate(R.id.action_global_homeFragment2)
                }
                is Resource.Error -> {
                    handleLoading(isLoading = false)
                    Toast.makeText(
                        requireContext(),
                        state.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Loading -> handleLoading(isLoading = true)
                else -> Unit
            }
        }
    }

    private fun initListeners(){
        with(binding){
            loginButton.setOnClickListener { handleLogin() }
            signUpButton.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_signUpFragment) }
        }
    }

    private fun handleLogin(){
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()

        viewModel.login(email, password)
    }

    private fun handleLoading(isLoading:Boolean){
        with(binding){
            if (isLoading) {
                loginButton.text = ""
                loginButton.isEnabled = false
                loginPb.visibility = View.VISIBLE
            }else{
                loginPb.visibility = View.GONE
                loginButton.text = getString(R.string.login__signup_button)
                loginButton.isEnabled = true
            }
        }
    }
}