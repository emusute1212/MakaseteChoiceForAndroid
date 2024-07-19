package io.github.emusute1212.makasetechoice.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.emusute1212.makasetechoice.R
import io.github.emusute1212.makasetechoice.ui.MakaseteChoiceTheme

@Composable
fun <T> Dropdown(
    modifier: Modifier = Modifier,
    menus: List<T>,
    onClickMenu: (target: T) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var currentMenu: T? by remember { mutableStateOf(null) }
    Row(
        modifier = modifier
            .defaultMinSize(
                minWidth = 100.dp,
                minHeight = 30.dp,
            )
            .shadow(
                elevation = 1.dp,
            )
            .clickable {
                expanded = true
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = currentMenu?.toString() ?: "",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 16.dp),
        )
        Image(
            painter = painterResource(id = R.drawable.arrow_drop_down_ic),
            contentDescription = null,
        )
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
    ) {
        menus.forEach {
            DropdownMenuItem(
                text = {
                    Text(
                        text = it.toString(),
                    )
                },
                onClick = {
                    expanded = false
                    currentMenu = it
                    onClickMenu(it)
                }
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true,
)
fun PreviewDropdown() {
    MakaseteChoiceTheme {
        Dropdown(
            menus = listOf(
                "aaaa",
                "bbbb",
                "cccc"
            ),
            onClickMenu = {}
        )
    }
}