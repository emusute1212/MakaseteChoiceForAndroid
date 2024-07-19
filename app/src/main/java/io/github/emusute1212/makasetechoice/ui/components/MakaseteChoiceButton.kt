package io.github.emusute1212.makasetechoice.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.emusute1212.makasetechoice.ui.MakaseteChoiceTheme

@Composable
fun MakaseteChoiceButton(
    label: String,
    buttonBackgroundColor: Color,
    onPress: () -> Unit,
) {
    Button(
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 2.dp,
            pressedElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors().copy(
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = buttonBackgroundColor,
        ),
        onClick = onPress,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Preview
@Composable
fun PreviewMakaseteChoiceButton() {
    MakaseteChoiceTheme {
        MakaseteChoiceButton(
            label = "てすと",
            buttonBackgroundColor = MaterialTheme.colorScheme.surface,
            onPress = {},
        )
    }
}
