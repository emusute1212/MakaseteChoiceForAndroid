package io.github.emusute1212.makasetechoice.ui.members

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
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
import io.github.emusute1212.makasetechoice.ui.MakaseteChoiceApp
import io.github.emusute1212.makasetechoice.ui.MakaseteChoiceTheme
import io.github.emusute1212.makasetechoice.ui.components.MemberItem
import io.github.emusute1212.makasetechoice.ui.members.adding_member.ADDING_MEMBER_SCREEN_ROUTE
import io.github.emusute1212.makasetechoice.ui.members.adding_member.nestedAddingMemberScreen

const val MEMBER_LIST_SCREEN_ROUTE = "member_list_screen"
fun NavGraphBuilder.memberListScreen() {
    composable(MEMBER_LIST_SCREEN_ROUTE) {
        MemberListScreenHost()
    }
}

@Composable
fun MemberListScreenHost() {
    val nestedNavController = rememberNavController()
    NavHost(
        navController = nestedNavController,
        startDestination = MEMBER_LIST_SCREEN_MAIN_ROUTE,
    ) {
        nestedMemberListScreen(
            nestedNavController = nestedNavController
        )
        nestedAddingMemberScreen(
            nestedNavController = nestedNavController
        )
    }
}

private const val MEMBER_LIST_SCREEN_MAIN_ROUTE = "main"
private fun NavGraphBuilder.nestedMemberListScreen(
    nestedNavController: NavController,
) {
    composable(MEMBER_LIST_SCREEN_MAIN_ROUTE) {
        MemberListScreen(
            nestedNavController = nestedNavController,
        )
    }
}

@Composable
private fun MemberListScreen(
    viewModel: MemberListViewModel = mavericksViewModel(),
    nestedNavController: NavController,
) {
    val state by viewModel.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.init()
    }
    MemberListSection(
        state = state,
        onAction = {
            when (it) {
                MemberListUiAction.ClickAddMember -> {
                    nestedNavController.navigate(
                        route = ADDING_MEMBER_SCREEN_ROUTE,
                    )
                }

                is MemberListUiAction.ClickDeleteMember -> {
                    viewModel.deleteMember(
                        member = it.member,
                    )
                }
            }
        },
    )
}

@Composable
private fun MemberListSection(
    state: MemberListUiState,
    onAction: MemberListAction,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (state.members.isEmpty()) {
            EmptyMember()
        } else {
            MemberList(
                members = state.members,
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
                    MemberListUiAction.ClickAddMember
                )
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_plus),
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
private fun MemberList(
    members: List<Member>,
    onAction: MemberListAction,
){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(members) {
            Column {
                MemberItem(
                    member = it,
                    modifier = Modifier
                        .padding(
                            horizontal = 10.dp,
                            vertical = 6.dp,
                        ),
                    onClickDeleteButton = {
                        onAction(
                            MemberListUiAction.ClickDeleteMember(
                                member = it
                            )
                        )
                    }
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            }
        }
    }
}

@Composable
private fun EmptyMember() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_user_alt_slash),
            contentDescription = "Empty member icon",
            modifier = Modifier
                .size(
                    size = 120.dp,
                ),
        )
        Text(
            text = stringResource(id = R.string.nothing_member_text),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
@Preview(
    showBackground = true,
)
fun PreviewMemberListSection() {
    MakaseteChoiceTheme {
        MemberListSection(
            state = MemberListUiState(
                members = listOf(
                    Member(
                        id = 0,
                        name = "aiueo",
                    ),
                    Member(
                        id = 1,
                        name = "かきくけこ",
                    ),
                    Member(
                        id = 1,
                        name = "あｋｄｓｌｆｊｌｆｇ；あｄｓｇｋはｋｊｌｄｆｇｈｓｊかｌｓｈｇｋｊｆｈｆｋじゃｄｓｈ",
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
fun PreviewMemberEmptySection() {
    MakaseteChoiceTheme {
        MemberListSection(
            state = MemberListUiState(
                members = emptyList()
            ),
            onAction = {},
        )
    }
}
