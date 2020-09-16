package io.github.emusute1212.makasetechoice.util.messenger

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseMessengerViewModel<T : BaseMessage> : ViewModel() {
    @Suppress("UNCHECKED_CAST")
    private val _message = MutableLiveData<T>(BaseMessage.NULL as T)
    val message: LiveData<T>
        get() = _message

    @MainThread
    protected fun sendMessage(sendingMessage: T) {
        Log.i(TAG, "send message : $sendingMessage")
        _message.value = sendingMessage
    }

    companion object {
        private val TAG = BaseMessengerViewModel::class.java.simpleName
    }
}