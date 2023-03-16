//package com.batueksi.tekrar.presentation.fragment
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.Observer
//import androidx.navigation.fragment.findNavController
//import com.batueksi.tekrar.databinding.FragmentSignUpBinding
//import com.google.android.material.snackbar.Snackbar
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class SignUpFragment : Fragment() {
//
//    private lateinit var binding : FragmentSignUpBinding
//    private val viewModel: AuthViewModel by viewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentSignUpBinding.inflate(inflater, container, false)
//
//
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.buttonContinue.setOnClickListener {
//            val email = binding.editEmail.text.toString()
//            val password = binding.editPassword.text.toString()
//            val confirmPassword = binding.editUsername.text.toString()
//
//            val result = UtilsMethods.validationRegister(email, password, confirmPassword)
//
//            if (result.successful) {
//                binding.buttonContinue.isEnabled = false
//                binding.progressCircular.visibility = View.VISIBLE
//
//                viewModel.register(email, password)
//
//                viewModel.verify.observe(viewLifecycleOwner, Observer {
//                    if (it) {
//                        registrationSuccessful()
//                        findNavController()
//                            .navigateUp()
//                    }
//                })
//            } else {
//                binding.buttonContinue.isEnabled = true
//                binding.progressCircular.visibility = View.GONE
//                Snackbar.make(binding.buttonContinue, "${result.error}", Snackbar.LENGTH_LONG)
//                    .show()
//            }
//        }
//
//    }
//
//    private fun registrationSuccessful() {
//        viewModel.message.observe(viewLifecycleOwner, Observer {
//            binding.buttonContinue.isEnabled = true
//            binding.progressCircular.visibility = View.GONE
//            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
//        })
//    }
//}