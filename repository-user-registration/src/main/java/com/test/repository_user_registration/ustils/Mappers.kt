package com.test.repository_user_registration.domain

import com.test.core_db.entities.UserEntity

fun UserEntity.toUserResult(): UserResult{
    return UserResult(
        name = this.name,
        lastName = this.lastName,
        email = this.email,
        password = this.password
    )
}

fun UserResult.toUserEntity(): UserEntity{
    return UserEntity(
        name = this.name,
        lastName = this.lastName,
        email = this.email,
        password = this.password
    )
}