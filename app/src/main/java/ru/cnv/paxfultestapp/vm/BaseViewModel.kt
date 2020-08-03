package ru.cnv.paxfultestapp.vm

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(val viewModelDisposable: CompositeDisposable = CompositeDisposable()) :
    ViewModel() {

    override fun onCleared() {
        super.onCleared()
        viewModelDisposable.clear()
    }
}