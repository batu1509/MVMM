package com.batueksi.moviesapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.batueksi.moviesapp.R
import com.batueksi.moviesapp.databinding.FragmentSettingsBinding
import com.batueksi.moviesapp.presentation.viewmodel.SettingsViewModel
import com.batueksi.moviesapp.util.AlertDialogUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SettingsViewModel by viewModels()
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        collectDataLifecycleAware()
        setListenerSwitch()

        binding.userEmail.text = user?.email

        binding.Logout.setOnClickListener {
            AlertDialogUtil.showAlertDialog(
                context = requireContext(),
                title = R.string.are_you_sure_log_out,
                message = R.string.log_out_message,
                positiveBtnMessage = R.string.log_out,
                negativeBtnMessage = R.string.cancel,
                onClickPositiveButton = {
                    logout()
                }
            )
        }

    }

    private fun logout() = lifecycleScope.launch {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        viewModel.logoutUser()
        findNavController().navigate(R.id.loginFragment)
    }

    private fun setListenerSwitch() {
        binding.switchDarkTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                updateUIMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                updateUIMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun updateUIMode(uiMode: Int) {
        viewModel.updateUIMode(uiMode)
        AppCompatDelegate.setDefaultNightMode(uiMode)
    }

    private fun collectDataLifecycleAware() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED)
            {
                launch {
                    viewModel.getUIMode().collectLatest { uiMode ->
                        binding.switchDarkTheme.isChecked =
                            uiMode == AppCompatDelegate.MODE_NIGHT_YES
                    }
                }

            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}