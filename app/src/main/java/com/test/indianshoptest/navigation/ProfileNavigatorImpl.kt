package com.test.indianshoptest.navigation

import androidx.navigation.NavController
import com.test.feature_profile.presentation.ProfileNavigator
import com.test.indianshoptest.R

class ProfileNavigatorImpl: ProfileNavigator {
    override fun navigateToSignIn() {
        navController?.navigate(R.id.action_profileFragment_to_singInFragment)
    }

    override var navController: NavController? = null
}