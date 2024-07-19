package io.github.emusute1212.makasetechoice.ui.components.bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import io.github.emusute1212.makasetechoice.ui.navigations.BottomNavigationMenu

@Composable
fun BottomNavigationBar(
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    NavigationBar {
        BottomNavigationMenu.entries.forEach { item ->
            NavigationBarItem(
                icon = {
                    Image(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = stringResource(id = item.displayNameRes),
                        colorFilter = ColorFilter.tint(
                            color = MaterialTheme.colorScheme.onBackground,
                        ),
                        modifier = Modifier
                            .size(
                                size = 22.dp,
                            ),
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.displayNameRes),
                        style = MaterialTheme.typography.labelSmall,
                    )
                },
                colors = NavigationBarItemDefaults.colors().copy(
                    selectedIndicatorColor = MaterialTheme.colorScheme.primary,
                ),
                selected = navBackStackEntry?.destination?.route == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(BottomNavigationMenu.MemberList.route)
                    }
                }
            )
        }
    }
}
