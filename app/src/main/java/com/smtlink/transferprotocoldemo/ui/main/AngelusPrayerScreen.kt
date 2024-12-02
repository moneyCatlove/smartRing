package com.example.smartring.ui.theme.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartring.R

@Composable
fun AngelusPrayerScreen(navController: NavController) {
    // 임시로 스위치 상태 value 값 정함
    var isSwitchOn06 by remember { mutableStateOf(true) }
    var isSwitchOn12 by remember { mutableStateOf(true) }
    var isSwitchOn18 by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "삼종기도",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 알림 설정 박스
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            NotificationSwitchRow(time = "06:00", isChecked = isSwitchOn06, onCheckedChange = { isSwitchOn06 = it })
            NotificationSwitchRow(time = "12:00", isChecked = isSwitchOn12, onCheckedChange = { isSwitchOn12 = it })
            NotificationSwitchRow(time = "18:00", isChecked = isSwitchOn18, onCheckedChange = { isSwitchOn18 = it })
        }
    }
}

// 알림 설정 행을 위한
@Composable
fun NotificationSwitchRow(time: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = time, fontSize = 16.sp, color = Color.Black)
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
    }
}
