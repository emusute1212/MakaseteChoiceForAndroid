package io.github.emusute1212.makasetechoice.ui.settings

import androidx.annotation.StringRes
import io.github.emusute1212.makasetechoice.R

enum class SettingMenu(
    @StringRes
    val stringRes: Int,
) {
    ABOUT_APP(R.string.about_app),
    LICENSE(R.string.settings_oss_lib),
}
