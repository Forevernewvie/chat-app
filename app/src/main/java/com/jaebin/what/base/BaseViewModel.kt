package com.jaebin.what.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

class BaseViewModel :  ViewModel() {


    protected fun <T> LiveData<T>.value(v: T) {
        if (this is MutableLiveData<T>) {
            value = v
        }
    }

    protected val compositeDisposable = CompositeDisposable()

    protected fun <T> liveData(): LiveData<T> = MutableLiveData()
    protected fun <T> liveData(v: T): LiveData<T> = MutableLiveData(v)



    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


}