package com.example.smartring.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConnectionStatus(
    isConnected: Boolean,
    batteryLevel: Int?,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier =
                Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(if (isConnected) Color.Green else Color.Gray),
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = if (isConnected) "연결됨 | 배터리 : $batteryLevel%" else "연결없음",
            fontSize = 16.sp,
        )
    }
}
