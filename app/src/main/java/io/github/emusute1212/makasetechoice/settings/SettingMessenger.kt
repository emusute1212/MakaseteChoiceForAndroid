package io.github.emusute1212.makasetechoice.settings


sealed class SettingMessenger {
    object AboutApp : SettingMessenger()
    object OssLib : SettingMessenger()
}