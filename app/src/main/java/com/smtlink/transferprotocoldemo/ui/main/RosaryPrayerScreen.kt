package com.example.smartring.ui.theme.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RosaryPrayerScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabTitles = listOf("1주", "1개월", "1년")
    val prayerRecords =
        listOf(
            PrayerRecord("5월 20일", "5 단"),
            PrayerRecord("5월 21일", "4 단"),
            PrayerRecord("5월 22일", "10 단"),
            PrayerRecord("5월 23일", "5 단"),
            PrayerRecord("5월 24일", "4 단"),
            PrayerRecord("5월 25일", "5 단"),
            PrayerRecord("5월 26일", "5 단"),
        )

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopAppBar(
            title = { Text(text = "묵주기도", fontSize = 24.sp, fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        tint = Color.Black,
                        modifier = Modifier.size(18.dp),
                    )
                }
            },
            colors =
                TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black,
                ),
        )

        Spacer(modifier = Modifier.height(16.dp))

        TabRow(
            selectedTabIndex = selectedTab,
            modifier = Modifier.fillMaxWidth(),
            contentColor = Color.Black,
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                        )
                    },
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "5 단", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "기도를 시작 하시려면 클릭 하세요", color = Color.Gray)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "주간 묵주기도", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5F5)),
        ) {
            items(prayerRecords) { record ->
                PrayerRecordItem(record)
            }
        }
    }
}

data class PrayerRecord(
    val date: String,
    val count: String,
)

@Composable
fun PrayerRecordItem(record: PrayerRecord) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = "Heart Icon",
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = record.date, fontSize = 16.sp)
        }
        Text(text = record.count, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}
