package io.github.emusute1212.makasetechoice.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor() : ViewModel() {
    private val _message = MutableSharedFlow<SettingMessenger>()
    val message: SharedFlow<SettingMessenger>
        get() = _message
    val items = MutableStateFlow(SettingMenuItems.values().toList())

    fun onClickItem(item: SettingMenuItems) {
        viewModelScope.launch {
            when (item) {
                SettingMenuItems.ABOUT_APP -> _message.emit(SettingMessenger.AboutApp)
                SettingMenuItems.OSS_LIB -> _message.emit(SettingMessenger.OssLib)
            }.let {}
        }
    }
}