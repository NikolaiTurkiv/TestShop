package com.test.repository_user_registration.data

import android.util.Log
import com.test.core_db.dao.UserDao
import com.test.repository_user_registration.domain.UserRegistrationRepository
import com.test.repository_user_registration.domain.UserResult
import com.test.repository_user_registration.domain.toUserEntity
import com.test.repository_user_registration.domain.toUserResult
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class UserRegistrationRepositoryImpl(
    private val bd: UserDao
) : UserRegistrationRepository {

    override fun fullInfo(): Single<List<UserResult>> {
        return bd.getUsers().map { list->
            list.map { it.toUserResult() }
        }
    }

    override fun insertUser(user: UserResult) {
        Single.just(user)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                bd.insert(it.toUserEntity())
            }, {
                Log.d("ERROR_INSERT_USER",it.message.toString())
            })
    }

    override fun removeAll() {

    }
}