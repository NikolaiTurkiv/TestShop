package com.test.feature_home

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.test.android_utils.viewBinding
import com.test.feature_home.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    val binding by viewBinding<FragmentLoginBinding>()
    val viewModel by viewModels<RegistrationViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.imgHidePassword.setOnClickListener {
            passwordVisibility()
        }
        binding.buttonLogin.setOnClickListener {
            viewModel.checkLogin(
                binding.editFirstNameLogin.text.toString(),
                binding.editPassword.text.toString()
            )
        }
    }

    private fun initObservers() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) {

            if (it.editFields?.isNameNotEmpty == false) {
                binding.tvNameErrorLogin.visibility = View.VISIBLE
                binding.tvNameErrorLogin.text =
                    getString(com.test.core_resources.R.string.field_cant_be_empty)
            } else {
                binding.tvNameErrorLogin.visibility = View.INVISIBLE
            }
            if (it.editFields?.isPasswordNotEmpty == false) {
                binding.tvPasswordErrorLogin.visibility = View.VISIBLE
                binding.tvPasswordErrorLogin.text =
                    getString(com.test.core_resources.R.string.field_cant_be_empty)
            } else {
                binding.tvPasswordErrorLogin.visibility = View.INVISIBLE
            }

            if (it.containInDb == false) {
                binding.tvNameErrorLogin.visibility = View.VISIBLE
                binding.tvPasswordErrorLogin.visibility = View.VISIBLE

                binding.tvNameErrorLogin.text =
                    getString(com.test.core_resources.R.string.no_such_user_exists)
                binding.tvPasswordErrorLogin.text =
                    getString(com.test.core_resources.R.string.no_such_user_exists)

            } else {
                binding.tvNameErrorLogin.visibility = View.INVISIBLE
                binding.tvPasswordErrorLogin.visibility = View.INVISIBLE
            }

        }
    }

    private fun passwordVisibility() {
        if (binding.editPassword.transformationMethod is PasswordTransformationMethod) {
            binding.editPassword.transformationMethod = null
            binding.editPassword.setSelection(binding.editPassword.length())
        } else {
            binding.editPassword.transformationMethod =
                PasswordTransformationMethod.getInstance()
            binding.editPassword.setSelection(binding.editPassword.length())
        }
    }


}
