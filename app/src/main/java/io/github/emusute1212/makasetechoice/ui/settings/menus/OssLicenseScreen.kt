package io.github.emusute1212.makasetechoice.ui.settings.menus

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer

const val OSS_LICENSE_SCREEN_ROUTE = "oss_license_screen"
fun NavGraphBuilder.nestedOssLicenseScreen() {
    composable(OSS_LICENSE_SCREEN_ROUTE) {
        OssLicenseScreen()
    }
}

@Composable
private fun OssLicenseScreen() {
    LibrariesContainer(
        Modifier.fillMaxSize()
    )
}