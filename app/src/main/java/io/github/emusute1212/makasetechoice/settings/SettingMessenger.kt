package io.github.emusute1212.makasetechoice.settings

import io.github.emusute1212.makasetechoice.util.messenger.BaseMessage

sealed class SettingMessenger : BaseMessage {
    object OssLib : SettingMessenger()
}