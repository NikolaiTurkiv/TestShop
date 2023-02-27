package com.test.repository_user_registration.domain

import io.reactivex.rxjava3.core.Single

interface UserRegistrationRepository {
    fun fullInfo(): Single<List<UserResult>>
    fun insertUser(user: UserResult)
    fun removeAll()
}