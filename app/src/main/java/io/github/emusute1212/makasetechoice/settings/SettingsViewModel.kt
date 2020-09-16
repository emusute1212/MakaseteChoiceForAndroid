package io.github.emusute1212.makasetechoice.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.emusute1212.makasetechoice.util.messenger.BaseMessengerViewModel
import javax.inject.Inject

class SettingsViewModel @Inject constructor() : BaseMessengerViewModel<SettingMessenger>() {
    private val _items = MutableLiveData<List<SettingMenuItems>>(SettingMenuItems.values().toList())
    val items: LiveData<List<SettingMenuItems>>
        get() = _items

    fun onClickItem(item: SettingMenuItems) {
        when (item) {
            SettingMenuItems.ABOUT_APP -> sendMessage(SettingMessenger.AboutApp)
            SettingMenuItems.OSS_LIB -> sendMessage(SettingMessenger.OssLib)
        }.let {}
    }
}