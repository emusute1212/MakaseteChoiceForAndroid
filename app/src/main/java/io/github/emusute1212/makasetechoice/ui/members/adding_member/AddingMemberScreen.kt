package io.github.emusute1212.makasetechoice.ui.members.adding_member

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import io.github.emusute1212.makasetechoice.ui.MakaseteChoiceTheme
import io.github.emusute1212.makasetechoice.ui.components.MakaseteChoiceButton

const val ADDING_MEMBER_SCREEN_ROUTE = "adding_member_screen"
fun NavGraphBuilder.nestedAddingMemberScreen(
    nestedNavController: NavController,
) {
    dialog(ADDING_MEMBER_SCREEN_ROUTE) {
        AddingMemberScreen(
            nestedNavController = nestedNavController,
        )
    }
}

@Composable
fun AddingMemberScreen(
    viewModel: AddingMemberViewModel = mavericksViewModel(),
    nestedNavController: NavController,
) {
    val state = viewModel.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.stateFlow.collect {
            when (it.result) {
                AddingResult.NOT_STARTED,
                AddingResult.PROGRESS -> Unit

                AddingResult.FINISH,
                AddingResult.ERROR -> {
                    nestedNavController.popBackStack()
                }
            }
        }
    }
    AddingMember(
        state = state.value,
        onAction = {
            when (it) {
                AddingMemberUiAction.AddMember -> {
                    viewModel.createMember()
                }

                AddingMemberUiAction.Close -> {
                    nestedNavController.popBackStack()
                }

                is AddingMemberUiAction.InputMember -> {
                    viewModel.inputMember(it.currentValue)
                }
            }
        }
    )
}

@Composable
private fun AddingMember(
    state: AddingMemberUiState,
    onAction: AddingMemberAction,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(
                    size = 16.dp,
                )
            )
            .padding(
                horizontal = 22.dp,
                vertical = 10.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            value = state.memberName,
            onValueChange = {
                onAction(
                    AddingMemberUiAction.InputMember(
                        currentValue = it,
                    )
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.colors().copy(
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            ),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.input_member_name_hint),
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row {
            MakaseteChoiceButton(
                label = stringResource(id = R.string.cancel),
                buttonBackgroundColor = MaterialTheme.colorScheme.surface,
                onPress = {
                    onAction(
                        AddingMemberUiAction.Close
                    )
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            MakaseteChoiceButton(
                label = stringResource(id = R.string.add),
                buttonBackgroundColor = MaterialTheme.colorScheme.tertiary,
                onPress = {
                    onAction(
                        AddingMemberUiAction.AddMember
                    )
                }
            )
        }
    }
}

@Composable
@Preview
fun PreviewAddingMemberScreen() {
    MakaseteChoiceTheme {
        AddingMember(
            state = AddingMemberUiState(
                memberName = "",
            ),
            onAction = {},
        )
    }
}
