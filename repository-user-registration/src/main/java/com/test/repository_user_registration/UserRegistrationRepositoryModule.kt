package com.test.repository_user_registration

import com.test.core_db.dao.UserDao
import com.test.repository_user_registration.data.UserRegistrationRepositoryImpl
import com.test.repository_user_registration.domain.UserRegistrationRepository
import com.test.repository_user_registration.domain.UserRegistrationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserRegistrationRepositoryModule {

    @Provides
    @Singleton
    fun provideUserRegistration(db: UserDao): UserRegistrationRepository =
        UserRegistrationRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideUserRegistrationUseCase(userRegistrationRepository: UserRegistrationRepository) =
        UserRegistrationUseCase(userRegistrationRepository)

}
