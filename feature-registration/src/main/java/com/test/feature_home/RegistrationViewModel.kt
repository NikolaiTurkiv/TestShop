package com.test.feature_home

import android.util.Log
import android.util.Patterns
import com.mediapark.lib_android_base.mvi.MviAction
import com.mediapark.lib_android_base.mvi.MviViewState
import com.test.lib_android_utils.BaseViewModel
import com.test.repository_user_registration.domain.UserRegistrationUseCase
import com.test.repository_user_registration.domain.UserResult
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val userRegistrationUseCase: UserRegistrationUseCase,
    private val registrationNavigator: RegistrationNavigator
) : BaseViewModel<RegistrationViewModel.ViewState, RegistrationViewModel.Action>(ViewState()) {

    init {
        getUsers()
    }

    private fun getUsers() {
        userRegistrationUseCase.users
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                setListToViewState(it)
                Log.d("GET_USERS_DB", it.toString())
            }, {
                errorFromDb(it.message.toString())
            })
    }

    private fun errorFromDb(error: String) {
        sendAction(Action.Error(error))
    }


    data class ViewState(
        val usersList: List<UserResult>? = null,
        val user: UserResult? = null,
        val error: String? = null,
        val containInDb: Boolean? = null,
        val editFields: EditFields? = null,
    ) : MviViewState


    private fun setListToViewState(list: List<UserResult>) {
        sendAction(Action.UserFromDb(list))
    }

    data class EditFields(
        val isNameNotEmpty: Boolean? = null,
        val isLastNameNotEmpty: Boolean? = null,
        val isEmailNotEmpty: Boolean? = null,
        val isEmailValid: Boolean? = null,
    )

    sealed class Action() : MviAction {
        object DoNothing : Action()
        data class Error(val error: String?) : Action()
        data class UserFromDb(val list: List<UserResult>?) : Action()
        data class IsUserContainInDb(val isContain: Boolean) : Action()
        data class ValidateFields(val fields: EditFields) : Action()
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.DoNothing -> state.copy()
        is Action.Error -> state.copy(error = viewAction.error)
        is Action.UserFromDb -> state.copy(usersList = viewAction.list)
        is Action.IsUserContainInDb -> state.copy(containInDb = viewAction.isContain)
        is Action.ValidateFields -> state.copy(editFields = viewAction.fields)
    }

    fun validateFieldsAndSave(name: String, lastName: String, email: String) {
        val fields = EditFields(
            isNameNotEmpty = name.isNotEmpty(),
            isLastNameNotEmpty = lastName.isNotEmpty(),
            isEmailNotEmpty = email.isNotEmpty(),
            isEmailValid = isEmailValid(email)
        )

        val user = UserResult(name,lastName,email,"1111")

        sendAction(Action.ValidateFields(fields))
        sendAction(Action.IsUserContainInDb(isUserContainsInDb(user)))

        if (fields.isNameNotEmpty == true &&
            fields.isLastNameNotEmpty == true &&
            fields.isEmailNotEmpty == true &&
            fields.isEmailValid == true &&
            !isUserContainsInDb(user)
        ){
            saveUserToDb(user)
        }


    }

    private fun isEmailValid(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isUserContainsInDb(user: UserResult): Boolean {
        return state.usersList?.contains(user) ?: false
    }

    fun saveUserToDb(user: UserResult) {
        if (!isUserContainsInDb(user)) {
            userRegistrationUseCase.insertUser(user)
            goToLogin()
        } else
            sendAction(Action.IsUserContainInDb(true))
    }

    fun goToLogin() {
        registrationNavigator.goToLogin()
    }
}
