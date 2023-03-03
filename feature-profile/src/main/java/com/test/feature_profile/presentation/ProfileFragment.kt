package com.test.feature_profile.presentation

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.test.android_utils.viewBinding
import com.test.feature_profile.R
import com.test.feature_profile.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding<FragmentProfileBinding>()
    private val viewModel by viewModels<ProfileViewModel>()

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val selectedImage: Uri? = result.data?.data
                try{
                    binding.imgProfile.setImageURI(selectedImage)
                }catch (e:Exception){
                    Log.d(IMAGE_EXCEPTION,e.message.toString())
                }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initClickListeners()
    }

    private fun initClickListeners(){
        binding.tvChangePhoto.setOnClickListener {
            loadImageFromGallery()
        }

        binding.containerLogOut.setOnClickListener {
            viewModel.goToSignIn()
        }
    }

    private fun loadImageFromGallery() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        resultLauncher.launch(galleryIntent)
    }

    private fun initToolbar() {
        with(binding.profileToolbar) {
            setNavigationIcon(com.test.core_resources.R.drawable.ic_arrow_left)
            setNavigationOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    companion object{
        private const val IMAGE_EXCEPTION = "IMAGE_EXCEPTION"
    }
}