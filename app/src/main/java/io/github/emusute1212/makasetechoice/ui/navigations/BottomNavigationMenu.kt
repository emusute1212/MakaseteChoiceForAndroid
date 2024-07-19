package io.github.emusute1212.makasetechoice.ui.navigations

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import io.github.emusute1212.makasetechoice.R
import io.github.emusute1212.makasetechoice.ui.groups.GROUP_SCREEN_ROUTE
import io.github.emusute1212.makasetechoice.ui.members.MEMBER_LIST_SCREEN_ROUTE
import io.github.emusute1212.makasetechoice.ui.settings.SETTING_SCREEN_ROUTE

enum class BottomNavigationMenu(
    val route: String,
    @get:DrawableRes
    val iconRes: Int,
    @get:StringRes
    val displayNameRes: Int,
) {
    MemberList(
        route = MEMBER_LIST_SCREEN_ROUTE,
        iconRes = R.drawable.ic_user,
        displayNameRes = R.string.member,
    ),
    Group(
        route = GROUP_SCREEN_ROUTE,
        iconRes = R.drawable.ic_users,
        displayNameRes = R.string.choice,
    ),
    Setting(
        route = SETTING_SCREEN_ROUTE,
        iconRes = R.drawable.ic_cog,
        displayNameRes = R.string.setting,
    ),
    ;

    companion object {
        fun String.getBottomNavigationMenuByRoute(): BottomNavigationMenu? {
            return when (this) {
                MemberList.route -> MemberList
                Group.route -> Group
                Setting.route -> Setting
                else -> null
            }
        }
    }
}
