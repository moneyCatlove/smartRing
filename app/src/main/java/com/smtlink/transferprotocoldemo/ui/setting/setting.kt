package com.example.smartring.ui.theme.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
fun SettingsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7)) // 배경 색상
            .padding(16.dp) // 기본 패딩
    ) {
        // 장치 정보 카드
        DeviceInfoCard()

        Spacer(modifier = Modifier.height(16.dp)) // 카드와 리스트 간격

        // 설정 리스트
        SettingsList(navController = navController)
    }
}

@Composable
fun DeviceInfoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp), // 카드의 둥근 모서리
        colors = CardDefaults.cardColors(containerColor = Color.White) // 카드 배경색
    ) {
        Column(
            modifier = Modifier.padding(16.dp) // 카드 내부 패딩
        ) {
            Text(
                text = "Smart Rosary Ring",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "SRR01_4A64",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = "E8:3B:0E:07:4A:64\n3.00.06",
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_vector_3),
                        contentDescription = "배터리 상태",
                        tint = Color(0xFF4CAF50), // 배터리 상태 색상
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "연결됨 | 배터리: 100%",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
                Button(
                    onClick = { /* 연결 해제 기능 */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)) // 파란색 버튼
                ) {
                    Text(text = "연결해제", color = Color.White, fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun SettingsList(navController: NavController) {
    // 설정 항목 데이터
    val settingsItems = listOf(
        SettingsItem(
            title = "반지찾기",
            icon = R.drawable.ic_search,
            iconColor = Color(0xFF00BFA5),
            onClick = { navController.navigate("find_ring_screen") }
        ),
        SettingsItem(
            title = "건강 모니터링 설정",
            icon = R.drawable.ic_healthtrain,
            iconColor = Color(0xFFE57373),
            onClick = { navController.navigate("health_monitoring_screen") }
        ),
        SettingsItem(
            title = "단위 설정",
            icon = R.drawable.ic_units,
            iconColor = Color(0xFF8BC34A),
            onClick = { navController.navigate("unit_settings_screen") }
        ),
        SettingsItem(
            title = "배터리 부족 알림",
            icon = R.drawable.ic_battery_alert,
            iconColor = Color(0xFFFF9800),
            isSwitch = true
        ),
        SettingsItem(
            title = "펌웨어 업그레이드",
            icon = R.drawable.ic_firmware,
            iconColor = Color(0xFF03A9F4),
            onClick = { navController.navigate("firmware_upgrade_screen") }
        ),
        SettingsItem(
            title = "시스템 설정",
            icon = R.drawable.ic_setting,
            iconColor = Color(0xFFFFC107),
            onClick = { navController.navigate("system_settings_screen") }
        )
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        settingsItems.forEach { item ->
            SettingsItemRow(item)
        }
    }
}

// 데이터 클래스 정의
data class SettingsItem(
    val title: String,
    val icon: Int,
    val iconColor: Color,
    val onClick: (() -> Unit)? = null, // 클릭 시 동작 (옵션)
    val isSwitch: Boolean = false // Switch가 필요한 항목 여부
)

@Composable
fun SettingsItemRow(item: SettingsItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(12.dp)) // 배경 설정
            .padding(16.dp)
            .clickable(enabled = !item.isSwitch) { item.onClick?.invoke() }, // 클릭 가능
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            tint = item.iconColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = item.title,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f) // 텍스트 영역 확장
        )
        if (item.isSwitch) {
            var switchState by rememberSaveable { mutableStateOf(true) }
            Switch(
                checked = switchState,
                onCheckedChange = { switchState = it }
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.ic_vector_3),
                contentDescription = "Arrow",
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}
