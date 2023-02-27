package com.test.lib_android_utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mediapark.lib_android_base.mvi.MviAction
import com.mediapark.lib_android_base.mvi.MviViewState
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject

abstract class BaseViewModel<ViewState : MviViewState, ViewAction : MviAction>(initialState: ViewState) :
    ViewModel() {
    companion object {
        const val TAG = "BaseViewModel"
    }

    private val compositeDisposable = CompositeDisposable()

    private val _stateLiveData = MutableLiveData(initialState)
    protected val _actionsSubject = PublishSubject.create<ViewAction>();

    val stateLiveData = _stateLiveData as LiveData<ViewState>

    val state get() = _stateLiveData.value ?: throw Exception("state cannot be null")

    init {

        _actionsSubject
            .subscribe { onActionReceived(it) }
            .connect()
    }

    private fun onActionReceived(action: ViewAction) {
        val newState = onReduceState(action);
        val currentState = _stateLiveData.value!!;
        if (currentState == newState) return;

        _stateLiveData.value = newState;
        onStateChanged(currentState, newState);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "state change ${this.javaClass.simpleName} $newState")
        }
    }

    fun sendAction(viewAction: ViewAction) {
        _actionsSubject.onNext(viewAction);
    }

    protected open fun onStateChanged(prevState: ViewState, newState: ViewState) {};

    protected abstract fun onReduceState(viewAction: ViewAction): ViewState

    fun Disposable.connect(): Disposable {
        compositeDisposable.add(this)
        return this
    }

    fun clearConnections() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}