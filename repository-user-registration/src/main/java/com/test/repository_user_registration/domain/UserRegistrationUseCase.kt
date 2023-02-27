package com.test.repository_user_registration.domain

class UserRegistrationUseCase(
    private val userRegistrationRepository: UserRegistrationRepository
) {
    val users = userRegistrationRepository.fullInfo()

    fun insertUser(user: UserResult) {
        userRegistrationRepository.insertUser(user)
    }

    fun clearUsers() {
        userRegistrationRepository.removeAll()
    }
}