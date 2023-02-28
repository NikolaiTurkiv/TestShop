package com.test.feature_home

import com.test.android_utils.navigation.NavControllerHolder

interface RegistrationNavigator: NavControllerHolder {
    fun navigateToLogin()
    fun navigateToProductFromSingIn()
    fun navigateToProductFromLogIn()
}