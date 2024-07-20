package io.github.emusute1212.makasetechoice.ui.settings

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.emusute1212.makasetechoice.ui.MakaseteChoiceTheme
import io.github.emusute1212.makasetechoice.ui.settings.menus.ABOUT_APP_SCREEN_ROUTE
import io.github.emusute1212.makasetechoice.ui.settings.menus.OSS_LICENSE_SCREEN_ROUTE
import io.github.emusute1212.makasetechoice.ui.settings.menus.nestedAboutAppScreen
import io.github.emusute1212.makasetechoice.ui.settings.menus.nestedOssLicenseScreen

const val SETTING_SCREEN_ROUTE = "setting_screen"
fun NavGraphBuilder.settingScreen() {
    composable(SETTING_SCREEN_ROUTE) {
        SettingScreenHost()
    }
}

@Composable
fun SettingScreenHost() {
    val nestedNavController = rememberNavController()
    val slideDurationMillis = 400
    NavHost(
        navController = nestedNavController,
        startDestination = SETTING_SCREEN_MAIN_ROUTE,
        enterTransition = {
            slideIn(
                animationSpec = tween(slideDurationMillis)
            ) {
                IntOffset(it.width, 0)
            }
        },
        popEnterTransition = {
            slideIn(
                animationSpec = tween(slideDurationMillis)
            ) {
                IntOffset(-it.width, 0)
            }
        },
        exitTransition = {
            slideOut(
                animationSpec = tween(slideDurationMillis)
            ) {
                IntOffset(-it.width, 0)
            }
        },
        popExitTransition = {
            slideOut(
                animationSpec = tween(slideDurationMillis)
            ) {
                IntOffset(it.width, 0)
            }
        }
    ) {
        nestedSettingScreen(
            nestedNavController = nestedNavController
        )
        nestedAboutAppScreen()
        nestedOssLicenseScreen()
    }
}

private const val SETTING_SCREEN_MAIN_ROUTE = "main"
private fun NavGraphBuilder.nestedSettingScreen(
    nestedNavController: NavController,
) {
    composable(SETTING_SCREEN_MAIN_ROUTE) {
        SettingScreen(
            nestedNavController = nestedNavController,
        )
    }
}

@Composable
private fun SettingScreen(
    nestedNavController: NavController,
) {
    SettingMenus(
        menus = SettingMenu.entries,
        onItemClick = {
            when (it) {
                SettingMenu.ABOUT_APP -> {
                    nestedNavController.navigate(
                        route = ABOUT_APP_SCREEN_ROUTE
                    )
                }

                SettingMenu.LICENSE -> {
                    nestedNavController.navigate(
                        route = OSS_LICENSE_SCREEN_ROUTE
                    )
                }
            }
        },
    )
}

@Composable
private fun SettingMenus(
    menus: List<SettingMenu>,
    onItemClick: (menu: SettingMenu) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(menus) {
            Text(
                text = stringResource(id = it.stringRes),
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(it)
                    }
                    .padding(
                        vertical = 16.dp,
                        horizontal = 24.dp,
                    )
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        }
    }
}

@Composable
@Preview(
    showBackground = true
)
fun PreviewSettingScreen() {
    MakaseteChoiceTheme {
        SettingMenus(
            menus = SettingMenu.entries,
            onItemClick = {},
        )
    }
}
