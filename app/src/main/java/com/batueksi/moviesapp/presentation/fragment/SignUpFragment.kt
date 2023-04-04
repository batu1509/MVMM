package com.batueksi.moviesapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.batueksi.moviesapp.R
import com.batueksi.moviesapp.databinding.FragmentSignUpBinding
import com.batueksi.moviesapp.presentation.viewmodel.SignUpViewModel
import com.batueksi.moviesapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding : FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initListeners()
    }

    private fun initObservers(){
        viewModel.signUpState.observe(viewLifecycleOwner){state ->
            when(state){
                is Resource.Success -> {
                    handleLoading(isLoading = false)
                    activity?.onBackPressed()
                    Toast.makeText(
                        requireContext(),
                        "Sign up success",
                        Toast.LENGTH_SHORT
                    ).show()
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
            signUpButton.setOnClickListener {
                handleSignUp()
            }
            bBack.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }


    private fun handleSignUp(){
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()

        viewModel.signUp(email, password)
    }

    private fun handleLoading(isLoading:Boolean){
        with(binding){
            if (isLoading) {
                signUpButton.text = ""
                signUpButton.isEnabled = false
                signUpPb.visibility = View.VISIBLE
            }else{
                signUpPb.visibility = View.GONE
                signUpButton.text = getString(R.string.login__signup_button)
                signUpButton.isEnabled = true
            }
        }
    }
}