package io.github.emusute1212.makasetechoice.settings

import androidx.annotation.StringRes
import io.github.emusute1212.makasetechoice.R

enum class SettingMenuItems(@StringRes val stringRes: Int) {
    ABOUT_APP(R.string.about_app),
    OSS_LIB(R.string.settings_oss_lib),
}