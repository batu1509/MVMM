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
//import com.batueksi.tekrar.R
//import com.batueksi.tekrar.databinding.FragmentSignInBinding
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.android.material.snackbar.Snackbar
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class SignInFragment : Fragment() {
//
//    private lateinit var binding : FragmentSignInBinding
//    private val viewModel: AuthViewModel by viewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentSignInBinding.inflate(inflater, container, false)
//
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//
//
//        binding.textForget.setOnClickListener {
//
//            val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
//
//            findNavController()
//                .navigate(action)
//        }
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//        binding.buttonLogin.setOnClickListener {
//            val email = binding.editEmail.text.toString()
//            val password = binding.editPassword.text.toString()
//
//            val result = UtilsMethods.validationLogin(email, password)
//
//            if (result.successful) {
//                binding.buttonLogin.isEnabled = false
//                binding.progressCircular.visibility = View.VISIBLE
//
//                viewModel.login(email, password)
//
//            } else {
//                binding.buttonLogin.isEnabled = true
//                binding.progressCircular.visibility = View.GONE
//                Snackbar.make(binding.buttonLogin, "${result.error}", Snackbar.LENGTH_LONG).show()
//            }
//        }
//
//        viewModel.verify.observe(this, Observer {
//            if (it) {
//                binding.buttonLogin.isEnabled = true
//                binding.progressCircular.visibility = View.GONE
//
//                findNavController().popBackStack(R.id.navigation_auth, true)
//                findNavController().navigate(R.id.homeFragment2)
//
//            }
//        })
//
//        viewModel.message.observe(this, Observer {
//            binding.buttonLogin.isEnabled = true
//            binding.progressCircular.visibility = View.GONE
//            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
//
//        })
//    }
//
//}