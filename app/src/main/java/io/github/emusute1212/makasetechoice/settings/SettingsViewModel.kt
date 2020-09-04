package io.github.emusute1212.makasetechoice.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.emusute1212.makasetechoice.util.messenger.BaseMessengerViewModel

class SettingsViewModel : BaseMessengerViewModel<SettingMessenger>() {
    private val _items = MutableLiveData<List<SettingItems>>(SettingItems.values().toList())
    private val items: LiveData<List<SettingItems>>
        get() = _items

    fun onClickItem(item: SettingItems) {
        when (item) {
            SettingItems.OSS_LIB -> sendMessage(SettingMessenger.OssLib)
        }.let {}
    }
}