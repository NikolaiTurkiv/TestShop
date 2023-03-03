package com.test.indianshoptest.navigation.di

import com.test.android_utils.navigation.NavControllerHolder
import com.test.feature_home.presentation.RegistrationNavigator
import com.test.feature_profile.presentation.ProfileNavigator
import com.test.indianshoptest.navigation.ProfileNavigatorImpl
import com.test.indianshoptest.navigation.RegistrationNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    @Singleton
    fun provideRegisterNavigator(): RegistrationNavigator = RegistrationNavigatorImpl()

    @Provides
    @Singleton
    fun provideProfileNavigator(): ProfileNavigator = ProfileNavigatorImpl()


    @Provides
    @Singleton
    fun provideNavControllerHolders(
        registrationNavigator: RegistrationNavigator,
        profileNavigator: ProfileNavigator,
    ):Array<NavControllerHolder> =
        arrayOf(
            registrationNavigator,
            profileNavigator,
         )
}