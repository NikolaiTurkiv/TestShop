package com.test.core_db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* т.к. согласно тз пользователь не указывает свой пароль при регистрации,
*  только вводит его при повторном заходе. Поэтому установлен дефолтный пароль
* */
@Entity(tableName = "user_info")
data class UserEntity(
    @PrimaryKey
    val name: String,
    val lastName: String,
    val email: String,
    val password: String = "1111",
)