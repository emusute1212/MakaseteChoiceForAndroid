package io.github.emusute1212.makasetechoice.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor() : ViewModel() {
    private val _canSplashClose = MutableStateFlow(false)
    val canSplashClose: StateFlow<Boolean>
        get() = _canSplashClose
    private val _shouldSplashClose = MutableStateFlow(false)
    val shouldSplashClose: StateFlow<Boolean>
        get() = _shouldSplashClose

    fun init() {
        viewModelScope.launch {
            launch {
                delay(CLOSE_MILLIS)
                withContext(Dispatchers.Main) {
                    _canSplashClose.value = true
                }
            }
            launch {
                delay(FORCE_CLOSE_MILLIS)
                withContext(Dispatchers.Main) {
                    _shouldSplashClose.value = true
                }
            }
        }
    }

    companion object {
        private val CLOSE_MILLIS = TimeUnit.SECONDS.toMillis(3)
        private val FORCE_CLOSE_MILLIS = TimeUnit.SECONDS.toMillis(10)
    }
}