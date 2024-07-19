package io.github.emusute1212.makasetechoice.ui.components.bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import io.github.emusute1212.makasetechoice.ui.MakaseteChoiceTheme
import io.github.emusute1212.makasetechoice.ui.navigations.BottomNavigationMenu.Companion.getBottomNavigationMenuByRoute

@Composable
fun TopAppBar(
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    AppBarContents(
        title = navBackStackEntry
            ?.destination
            ?.route
            ?.getBottomNavigationMenuByRoute()
            ?.displayNameRes
            ?.let {
                stringResource(id = it)
            }
            ?: ""
    )
}

@Composable
private fun AppBarContents(
    title: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 20.dp,
                )
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.outline)
    }
}

@Preview
@Composable
fun PreviewAppBarContents() {
    MakaseteChoiceTheme {
        Scaffold(
            topBar = {
                AppBarContents(
                    title = "プレビュー",
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            )
        }
    }
}