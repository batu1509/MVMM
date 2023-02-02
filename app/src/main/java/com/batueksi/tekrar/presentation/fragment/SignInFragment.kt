package com.batueksi.tekrar.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.batueksi.tekrar.R
import com.batueksi.tekrar.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth


class SignInFragment : Fragment() {

    private lateinit var binding : FragmentSignInBinding
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        binding.textView.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        firebaseAuth = FirebaseAuth.getInstance()
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){

                    firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful){
                            findNavController().navigate(R.id.action_signInFragment_to_homeFragment2)
                        }else{
                            Toast.makeText(context, it.exception.toString() , Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(context, "Empty fields are not allowed !!" , Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser != null){
            findNavController().navigate(R.id.action_signInFragment_to_homeFragment2)
        }
    }

}