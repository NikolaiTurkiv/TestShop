package com.test.feature_home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            viewModel.validateFieldsAndSave(
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

            if (it.editFields?.isNameNotEmpty == false){
                binding.editFirstName.error = getString(com.test.core_resources.R.string.field_cant_be_empty)
            }
            if(it.editFields?.isLastNameNotEmpty == false){
                binding.editLastName.error = getString(com.test.core_resources.R.string.field_cant_be_empty)
            }

            if(it.editFields?.isEmailNotEmpty == false){
                binding.editEmail.error = getString(com.test.core_resources.R.string.field_cant_be_empty)
            }

            if(it.editFields?.isEmailValid == false && it.editFields.isEmailNotEmpty == true){
                binding.editEmail.error = getString(
                    com.test.core_resources.R.string.email_is_not_valid,
                    binding.editEmail.text.toString(),
                    binding.editEmail.text.toString()
                )
            }
            Toast.makeText(requireContext(),it.containInDb.toString(),Toast.LENGTH_SHORT).show()
        }
    }


}
