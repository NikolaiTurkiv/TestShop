package com.test.indianshoptest.navigation

import androidx.navigation.NavController
import com.test.feature_home.presentation.RegistrationNavigator
import com.test.indianshoptest.R

class RegistrationNavigatorImpl : RegistrationNavigator {

    override fun navigateToLogin() {
        navController?.navigate(R.id.action_singInFragment_to_loginFragment)
    }

    override fun navigateToProductFromSingIn() {
         navController?.navigate(R.id.action_singInFragment_to_productFragment)
    }

    override fun navigateToProductFromLogIn() {
        navController?.navigate(R.id.action_loginFragment_to_productFragment)
    }

    override var navController: NavController? = null
}