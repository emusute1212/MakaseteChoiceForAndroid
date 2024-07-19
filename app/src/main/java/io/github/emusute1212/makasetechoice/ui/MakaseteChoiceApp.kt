package io.github.emusute1212.makasetechoice.ui

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.emusute1212.makasetechoice.ui.components.bar.BottomNavigationBar
import io.github.emusute1212.makasetechoice.ui.components.bar.TopAppBar
import io.github.emusute1212.makasetechoice.ui.groups.groupScreen
import io.github.emusute1212.makasetechoice.ui.members.MEMBER_LIST_SCREEN_ROUTE
import io.github.emusute1212.makasetechoice.ui.members.memberListScreen
import io.github.emusute1212.makasetechoice.ui.settings.settingScreen

@Composable
fun MakaseteChoiceApp() {
    MakaseteChoiceTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            MakaseteChoiceNavHost()
        }
    }
}

@Composable
fun MakaseteChoiceNavHost(
    navController: NavHostController = rememberNavController(),
) {
    val fadeDurationMillis = 400
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                navController = navController,
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
            )
        }
    ) { padding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            navController = navController,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(fadeDurationMillis)
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(fadeDurationMillis)
                )
            },
            startDestination = MEMBER_LIST_SCREEN_ROUTE,
        ) {
            memberListScreen()
            groupScreen()
            settingScreen()
        }
    }
}
