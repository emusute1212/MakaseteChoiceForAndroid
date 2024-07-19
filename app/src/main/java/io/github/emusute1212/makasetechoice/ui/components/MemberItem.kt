package io.github.emusute1212.makasetechoice.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.emusute1212.makasetechoice.R
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.ui.MakaseteChoiceTheme

@Composable
fun MemberItem(
    member: Member,
    modifier: Modifier = Modifier,
    onClickDeleteButton: (member: Member) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_user_circle),
            contentDescription = "Member icon",
            colorFilter = ColorFilter.tint(
                color = MaterialTheme.colorScheme.onSurface,
            ),
            modifier = Modifier
                .size(
                    size = 48.dp,
                ),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = member.name,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .weight(1f),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = "Close Button",
            colorFilter = ColorFilter.tint(
                color = MaterialTheme.colorScheme.outline,
            ),
            modifier = Modifier
                .clickable {
                    onClickDeleteButton(member)
                }
        )
    }
}

@Composable
@Preview(
    showBackground = true,
)
fun PreviewMemberItem() {
    MakaseteChoiceTheme {
        MemberItem(
            member = Member(
                id = 0,
                name = "aiueo",
            ),
            onClickDeleteButton = {}
        )
    }
}