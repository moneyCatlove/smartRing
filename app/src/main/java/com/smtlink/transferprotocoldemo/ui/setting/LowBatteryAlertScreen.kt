package com.example.smartring.ui.theme.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.navigation.NavController

@Composable
fun LowBatteryAlertScreen(navController: NavController) {
    var isEnabled by remember { mutableStateOf(true) }
    Column {
        Text(text = "배터리 부족 알림 설정")
        Switch(
            checked = isEnabled,
            onCheckedChange = { isEnabled = it }
        )
    }
}
