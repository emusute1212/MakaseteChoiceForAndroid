package io.github.emusute1212.makasetechoice.ui.groups.grouping_member

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import io.github.emusute1212.makasetechoice.R
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.ui.MakaseteChoiceTheme
import io.github.emusute1212.makasetechoice.ui.components.Dropdown
import io.github.emusute1212.makasetechoice.ui.components.MakaseteChoiceButton
import io.github.emusute1212.makasetechoice.ui.groups.grouping_member.ChoiceResult.ERROR
import io.github.emusute1212.makasetechoice.ui.groups.grouping_member.ChoiceResult.FINISH
import io.github.emusute1212.makasetechoice.ui.groups.grouping_member.ChoiceResult.NOT_STARTED
import io.github.emusute1212.makasetechoice.ui.groups.grouping_member.ChoiceResult.PROGRESS

const val GROUPING_MEMBER_SCREEN_ROUTE = "grouping_member_screen"
fun NavGraphBuilder.nestedGroupingMemberScreen(
    nestedNavController: NavController,
) {
    dialog(GROUPING_MEMBER_SCREEN_ROUTE) {
        GroupingMemberScreen(
            nestedNavController = nestedNavController,
        )
    }
}

@Composable
fun GroupingMemberScreen(
    viewModel: GroupingMemberViewModel = mavericksViewModel(),
    nestedNavController: NavController,
) {
    val state by viewModel.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.init()
        viewModel.stateFlow.collect {
            when (it.result) {
                NOT_STARTED,
                PROGRESS -> Unit

                FINISH,
                ERROR -> {
                    nestedNavController.popBackStack()
                }
            }
        }
    }
    GroupingMemberSection(
        state = state,
        onAction = {
            when (it) {
                GroupingMemberUiAction.Close -> {
                    nestedNavController.popBackStack()
                }

                GroupingMemberUiAction.DoChoice -> {
                    viewModel.createGroup()
                }

                is GroupingMemberUiAction.SelectSplitNumber -> {
                    viewModel.selectSplitNumber(it.splitNumber)
                }
            }
        },
    )
}

@Composable
private fun GroupingMemberSection(
    state: GroupingMemberUiState,
    onAction: GroupingMemberAction,
) {
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(
                    size = 16.dp,
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.member_number_title, state.members.count()),
            style = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.outline,
            ),
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.choice_number_title),
                style = MaterialTheme.typography.labelMedium,
            )
            Spacer(modifier = Modifier.width(12.dp))
            Dropdown(
                menus = (1..state.members.count()).toList(),
                onClickMenu = {
                    onAction(
                        GroupingMemberUiAction.SelectSplitNumber(it)
                    )
                },
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Row {
            MakaseteChoiceButton(
                label = stringResource(id = R.string.cancel),
                buttonBackgroundColor = MaterialTheme.colorScheme.surface,
                onPress = {
                    onAction(
                        GroupingMemberUiAction.Close
                    )
                }
            )
            Spacer(modifier = Modifier.width(12.dp))
            MakaseteChoiceButton(
                label = stringResource(id = R.string.do_choice),
                buttonBackgroundColor = MaterialTheme.colorScheme.tertiary,
                onPress = {
                    onAction(
                        GroupingMemberUiAction.DoChoice
                    )
                }
            )
        }
    }
}

@Composable
@Preview
fun PreviewGroupingMemberScreen() {
    MakaseteChoiceTheme {
        GroupingMemberSection(
            state = GroupingMemberUiState(
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
                currentSplitCount = 2,
            ),
            onAction = {},
        )
    }
}
