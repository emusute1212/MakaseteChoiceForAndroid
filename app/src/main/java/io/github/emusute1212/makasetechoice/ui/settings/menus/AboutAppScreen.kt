package io.github.emusute1212.makasetechoice.ui.settings.menus

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.emusute1212.makasetechoice.R
import io.github.emusute1212.makasetechoice.ui.MakaseteChoiceTheme

const val ABOUT_APP_SCREEN_ROUTE = "about_app_screen"
fun NavGraphBuilder.nestedAboutAppScreen() {
    composable(ABOUT_APP_SCREEN_ROUTE) {
        AboutAppScreen()
    }
}

@Composable
private fun AboutAppScreen() {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    val versionName = context.packageManager
        .getPackageInfo(context.packageName, 0)
        ?.versionName
        ?: "0.0.0.0"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                all = 8.dp,
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painterResource(id = R.drawable.big_logo),
                contentDescription = "App logo",
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                ),
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(
                id = R.string.version_label,
                versionName,
            ),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface,
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        val styledText = buildAnnotatedString {
            append(
                text = stringResource(id = R.string.author_label),
            )
            pushStringAnnotation(
                tag = "URL",
                annotation = "https://yosuke-miyanishi.com/"
            )
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline,
                )
            ) {
                append(
                    text = stringResource(id = R.string.author),
                )
            }
            pop()
        }
        ClickableText(
            text = styledText,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface,
            ),
            onClick = {
                styledText
                    .getStringAnnotations(
                        start = it,
                        end = it,
                    )
                    .firstOrNull()
                    ?.also { range ->
                        uriHandler.openUri(range.item)
                    }
            }
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun PreviewAboutAppScreen() {
    MakaseteChoiceTheme {
        AboutAppScreen()
    }
}