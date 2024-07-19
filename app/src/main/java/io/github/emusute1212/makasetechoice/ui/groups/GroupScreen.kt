package io.github.emusute1212.makasetechoice.ui.groups

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import io.github.emusute1212.makasetechoice.R
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.ui.MakaseteChoiceTheme
import io.github.emusute1212.makasetechoice.ui.components.MemberItem
import io.github.emusute1212.makasetechoice.ui.groups.grouping_member.GROUPING_MEMBER_SCREEN_ROUTE
import io.github.emusute1212.makasetechoice.ui.groups.grouping_member.nestedGroupingMemberScreen

const val GROUP_SCREEN_ROUTE = "group_screen"
fun NavGraphBuilder.groupScreen() {
    composable(GROUP_SCREEN_ROUTE) {
        GroupScreenHost()
    }
}

@Composable
fun GroupScreenHost() {
    val nestedNavController = rememberNavController()
    NavHost(
        navController = nestedNavController,
        startDestination = GROUP_SCREEN_MAIN_ROUTE,
    ) {
        nestedGroupScreen(
            nestedNavController = nestedNavController
        )
        nestedGroupingMemberScreen(
            nestedNavController = nestedNavController
        )
    }
}

private const val GROUP_SCREEN_MAIN_ROUTE = "main"
private fun NavGraphBuilder.nestedGroupScreen(
    nestedNavController: NavController,
) {
    composable(GROUP_SCREEN_MAIN_ROUTE) {
        GroupScreen(
            nestedNavController = nestedNavController,
        )
    }
}


@Composable
private fun GroupScreen(
    viewModel: GroupViewModel = mavericksViewModel(),
    nestedNavController: NavController,
) {
    val state by viewModel.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.init()
    }
    GroupSection(
        state = state,
        onAction = {
            when (it) {
                GroupUiAction.ClickGroupMember -> {
                    nestedNavController.navigate(
                        route = GROUPING_MEMBER_SCREEN_ROUTE,
                    )
                }

                is GroupUiAction.DeleteMember -> {
                    viewModel.deleteMember(
                        member = it.member,
                    )
                }
            }
        }
    )
}

@Composable
private fun GroupSection(
    state: GroupUiState,
    onAction: GroupAction,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (state.groups.isEmpty()) {
            EmptyGroup()
        } else {
            GroupList(
                groups = state.groups,
                onAction = onAction,
            )
        }
        FloatingActionButton(
            modifier = Modifier
                .padding(
                    end = 16.dp,
                    bottom = 16.dp,
                )
                .align(Alignment.BottomEnd),
            containerColor = MaterialTheme.colorScheme.primary,
            onClick = {
                onAction(
                    GroupUiAction.ClickGroupMember
                )
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_users),
                contentDescription = "Plus button",
                colorFilter = ColorFilter.tint(
                    color = MaterialTheme.colorScheme.onPrimary,
                ),
                modifier = Modifier
                    .size(
                        size = 16.dp,
                    )
            )
        }
    }
}

@Composable
private fun GroupList(
    groups: Map<String, List<Member>>,
    onAction: GroupAction,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(groups.toList()) {
            Column {
                Text(
                    text = it.first,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(
                            top = 26.dp,
                            bottom = 8.dp,
                            start = 8.dp,
                        ),
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                MemberList(
                    members = it.second,
                    onAction = onAction,
                )
            }
        }
    }
}

@Composable
private fun MemberList(
    members: List<Member>,
    onAction: GroupAction,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        members.forEach { member ->
            MemberItem(
                member = member,
                modifier = Modifier
                    .padding(
                        horizontal = 10.dp,
                        vertical = 6.dp,
                    ),
                onClickDeleteButton = {
                    onAction(
                        GroupUiAction.DeleteMember(
                            member = it
                        )
                    )
                },
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        }
    }
}
@Composable
private fun EmptyGroup() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_users_slash),
            contentDescription = "Empty group icon",
            modifier = Modifier
                .size(
                    size = 120.dp,
                ),
        )
        Text(
            text = stringResource(id = R.string.nothing_group_text),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
@Preview(
    showBackground = true,
)
fun PrivateGroupSection() {
    MakaseteChoiceTheme {
        GroupSection(
            state = GroupUiState(
                groups = mapOf(
                    "チーム1" to listOf(
                        Member(
                            id = 0,
                            name = "aiueo",
                        ),
                        Member(
                            id = 1,
                            name = "かきくけこ",
                        ),
                    ),
                    "チーム2" to listOf(
                        Member(
                            id = 2,
                            name = "あｋｄｓｌｆｊｌｆｇ；あｄｓｇｋはｋｊｌｄｆｇｈｓｊかｌｓｈｇｋｊｆｈｆｋじゃｄｓｈ",
                        ),
                    ),
                ),
            ),
            onAction = {},
        )
    }
}

@Composable
@Preview(
    showBackground = true,
)
fun PrivateGroupEmptySection() {
    MakaseteChoiceTheme {
        GroupSection(
            state = GroupUiState(
                groups = emptyMap(),
            ),
            onAction = {},
        )
    }
}