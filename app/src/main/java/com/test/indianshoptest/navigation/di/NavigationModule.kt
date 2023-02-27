package com.test.indianshoptest.navigation.di

import com.test.android_utils.navigation.NavControllerHolder
import com.test.feature_home.RegistrationNavigator
import com.test.indianshoptest.navigation.RegistrationNavigatorImpl
import dagger.Binds
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
    fun provideRegisterNavigator():RegistrationNavigator = RegistrationNavigatorImpl()

    @Provides
    @Singleton
    fun provideNavControllerHolders(
        registrationNavigator: RegistrationNavigator,
    ):Array<NavControllerHolder> =
        arrayOf(
            registrationNavigator,
        )
}