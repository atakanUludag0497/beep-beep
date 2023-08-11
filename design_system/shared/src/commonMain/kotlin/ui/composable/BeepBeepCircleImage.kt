package com.beepbeep.designSystem.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.beepbeep.designSystem.ui.theme.BeepBeepTheme
import com.beepbeep.designSystem.ui.theme.BeepBeepTheme.dimens

@Composable
fun BeepBeepCircleImage(
    painter: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    boxSize: Dp = 72.dp,
    imageSize: Dp = 32.dp,
    strokeWidth: Dp = dimens.strokeSmall,
    strokeColor: Color = BeepBeepTheme.color.onTertiary,
    backgroundColor: Color = BeepBeepTheme.color.surface,
    imageScale: ContentScale = ContentScale.Crop,
) {
    Box(
        modifier = modifier
            .clip(shape = CircleShape)
            .background(backgroundColor)
            .size(boxSize)
            .clickable { onClick() }
            .border(width = strokeWidth, color = strokeColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(imageSize),
            painter = painter,
            contentDescription = "",
            alignment = Alignment.Center,
            contentScale = imageScale
        )
    }
}