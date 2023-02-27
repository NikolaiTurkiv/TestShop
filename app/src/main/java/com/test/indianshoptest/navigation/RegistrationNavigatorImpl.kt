package com.test.indianshoptest.navigation

import androidx.navigation.NavController
import com.test.feature_home.RegistrationNavigator
import com.test.indianshoptest.R

class RegistrationNavigatorImpl : RegistrationNavigator {

    override fun goToLogin() {
        navController?.navigate(R.id.action_singInFragment_to_loginFragment)
    }

    override var navController: NavController? = null
}