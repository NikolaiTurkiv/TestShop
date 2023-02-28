package com.test.feature_home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.test.android_utils.viewBinding
import com.test.feature_home.databinding.FragmentSingInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingInFragment : Fragment(R.layout.fragment_sing_in) {

    val viewModel by viewModels<RegistrationViewModel>()
    val binding by viewBinding<FragmentSingInBinding>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initClickListeners()
    }


    private fun initClickListeners() {
        binding.loginTextButton.setOnClickListener {
            viewModel.goToLogin()
        }

        binding.signInButton.setOnClickListener {
            viewModel.checkSignInAndSaveUser(
                binding.editFirstName.text.toString(),
                binding.editLastName.text.toString(),
                binding.editEmail.text.toString()
            )
        }

    }

    private fun initObservers() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            Log.d("ITEMS_IN_DB", it.usersList.toString())
            Log.d("VALIDATEFIELD", it.editFields.toString())

            if (it.editFields?.isNameNotEmpty == false) {
                binding.tvErrorNameSigin.visibility = View.VISIBLE
                binding.tvErrorNameSigin.text =
                    getString(com.test.core_resources.R.string.field_cant_be_empty)
            } else {
                binding.tvErrorNameSigin.visibility = View.INVISIBLE
            }
            if (it.editFields?.isLastNameNotEmpty == false) {
                binding.tvErrorLastNameSinging.visibility = View.VISIBLE
                binding.tvErrorLastNameSinging.text =
                    getString(com.test.core_resources.R.string.field_cant_be_empty)
            } else {
                binding.tvErrorLastNameSinging.visibility = View.INVISIBLE
            }

            if (it.editFields?.isEmailNotEmpty == false) {
                binding.tvErrorEmailSigin.visibility = View.VISIBLE
                binding.tvErrorEmailSigin.text =
                    getString(com.test.core_resources.R.string.field_cant_be_empty)
            } else {
                binding.tvErrorEmailSigin.visibility = View.INVISIBLE
            }

            if (it.editFields?.isEmailValid == false && it.editFields.isEmailNotEmpty == true) {
                binding.tvErrorEmailSigin.visibility = View.VISIBLE
                binding.tvErrorEmailSigin.text = getString(
                    com.test.core_resources.R.string.email_is_not_valid,
                    binding.editEmail.text.toString(),
                    binding.editEmail.text.toString()
                )
            } else {
                binding.tvErrorEmailSigin.visibility = View.INVISIBLE
            }
            if (it.containInDb == true) {
                binding.tvErrorNameSigin.visibility = View.VISIBLE
                binding.tvErrorLastNameSinging.visibility = View.VISIBLE
                binding.tvErrorEmailSigin.visibility = View.VISIBLE

                binding.tvErrorNameSigin.text =
                    getString(com.test.core_resources.R.string.this_user_already_exists)
                binding.tvErrorEmailSigin.text =
                    getString(com.test.core_resources.R.string.this_user_already_exists)
                binding.tvErrorLastNameSinging.text =
                    getString(com.test.core_resources.R.string.this_user_already_exists)
            }else{
                binding.tvErrorNameSigin.visibility = View.VISIBLE
                binding.tvErrorLastNameSinging.visibility = View.VISIBLE
                binding.tvErrorEmailSigin.visibility = View.VISIBLE
            }
        }
    }


}
