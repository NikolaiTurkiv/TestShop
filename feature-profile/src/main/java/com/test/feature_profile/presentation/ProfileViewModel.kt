package com.test.feature_profile.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
   private val profileNavigator: ProfileNavigator
): ViewModel() {
    fun goToSignIn(){
        profileNavigator.navigateToSignIn()
    }
}