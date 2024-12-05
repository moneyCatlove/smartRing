package com.example.smartring.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.smtlink.transferprotocoldemo.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    // 현재 선택된 탭 상태 관리 (기본은 "catholic")
    var selectedTab by remember { mutableStateOf("catholic") }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color(0xFFF7F7F7), shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .padding(horizontal = 16.dp)
    ) {
        // 카톨릭 네비게이션
        BottomNavItem(
            label = "가톨릭",
            iconId = if (selectedTab == "catholic") R.drawable.ic_catholic else R.drawable.ic_catholicnav,
            isSelected = selectedTab == "catholic",
            onClick = {
                selectedTab = "catholic"
                navController.navigate("home")
            }
        )

        // 헬스케어 네비게이션
        BottomNavItem(
            label = "헬스케어",
            iconId = if (selectedTab == "healthcare") R.drawable.ic_bottomnavhealthcare else R.drawable.ic_healthcare,
            isSelected = selectedTab == "healthcare",
            onClick = {
                selectedTab = "healthcare"
                navController.navigate("health_main")
            }
        )

        BottomNavItem(
            label = "설정",
            iconId = if (selectedTab == "settings") R.drawable.ic_settingnav else R.drawable.ic_settings,
            isSelected = selectedTab == "settings",
            onClick = {
                selectedTab = "settings"
                navController.navigate("settings_screen")
            }
        )
    }
}

@Composable
fun BottomNavItem(label: String, iconId: Int, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(4.dp)
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = label,
            modifier = Modifier
                .size(37.dp)
                .padding(bottom = 4.dp),
            tint = Color.Unspecified
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = if (isSelected) Color(0xFF007AFF) else Color.Black // 선택 여부에 따른 텍스트 색상 변경
        )
    }
}
