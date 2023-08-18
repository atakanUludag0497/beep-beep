package org.thechance.common.presentation.composables.table

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.beepbeep.designSystem.ui.theme.Theme

@Composable
fun TotalItemsIndicator(
    modifier: Modifier = Modifier,
    numberItemInPage: Int,
    totalItems: Int,
    onItemPerPageChange: (String) -> Unit,
    itemType: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        BasicTextField(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Theme.colors.contentBorder,
                    shape = RoundedCornerShape(8.dp)
                ).padding(vertical = 8.dp).width(40.dp),
            value = "$numberItemInPage",
            onValueChange = onItemPerPageChange,
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                color = Theme.colors.contentPrimary,
                fontStyle = Theme.typography.title.fontStyle
            ),
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
        Text(
            "$itemType out of $totalItems ${itemType}s",
            style = Theme.typography.body,
            color = Theme.colors.contentSecondary
        )
    }
}