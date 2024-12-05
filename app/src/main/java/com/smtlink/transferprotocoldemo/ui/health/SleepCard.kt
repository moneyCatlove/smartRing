package com.example.smartring.ui.theme.healthcare

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.smtlink.transferprotocoldemo.ui.controller.SleepData
import com.smtlink.transferprotocoldemo.R

@Composable
fun SleepCard(navController: NavController, sleepData: SleepData?) {
    val isLoading = remember { mutableStateOf(sleepData == null) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(androidx.compose.ui.graphics.Color.White, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        if (isLoading.value) {
            // 로딩 중
            LoadingView()
        } else {
            sleepData?.let { data ->
                Column {
                    // 헤더
                    Header(navController, data)

                    Spacer(modifier = Modifier.height(16.dp))

                    // 그래프
                    SleepChart(data)

                    Spacer(modifier = Modifier.height(8.dp))

                    // 수면 요약
                    SleepSummary(data)

                    Spacer(modifier = Modifier.height(16.dp))

                    // 푸터
                    Footer(data)
                }
            } ?: run {
                Text(
                    text = "수면 데이터를 가져올 수 없습니다.",
                    fontSize = 16.sp,
                    color = androidx.compose.ui.graphics.Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}


@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "데이터 로딩 중...", fontSize = 16.sp, color = androidx.compose.ui.graphics.Color.Gray)
    }
}

@Composable
fun Header(navController: NavController, data: SleepData) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                text = "수면",
                fontSize = 20.sp,
                color = androidx.compose.ui.graphics.Color(0xFF00BFA5)
            )
            Text(
                text = data.date,
                fontSize = 14.sp,
                color = androidx.compose.ui.graphics.Color.Gray
            )
        }
        IconButton(
            onClick = {
                navController.navigate("sleep_day_detail_screen")
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_vector_3),
                contentDescription = "Go to Details",
                modifier = Modifier.size(24.dp),
                tint = androidx.compose.ui.graphics.Color.Gray
            )
        }
    }
}

@Composable
fun SleepChart(data: SleepData) {
    AndroidView(
        factory = { context ->
            PieChart(context).apply {
                val entries = listOf(
                    PieEntry(data.deepSleepMinutes.toFloat(), "깊은 수면"),
                    PieEntry(data.lightSleepMinutes.toFloat(), "얕은 수면")
                )
                val dataSet = PieDataSet(entries, "").apply {
                    colors = listOf(
                        Color.parseColor("#00BFA5"),
                        Color.parseColor("#E0E0E0")
                    )
                    sliceSpace = 2f
                    setDrawValues(false)
                }
                this.data = PieData(dataSet)

                holeRadius = 80f
                transparentCircleRadius = 85f
                setDrawEntryLabels(false)
                description.isEnabled = false
                legend.isEnabled = false
                isRotationEnabled = false
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}

@Composable
fun SleepSummary(data: SleepData) {
    val totalSleepTime = "${data.totalSleepMinutes / 60}시간 ${data.totalSleepMinutes % 60}분"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = totalSleepTime,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = androidx.compose.ui.graphics.Color.Black
        )
        Text(
            text = "좋음",
            fontSize = 16.sp,
            color = androidx.compose.ui.graphics.Color.Gray
        )
    }
}

@Composable
fun Footer(data: SleepData) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = data.date,
            fontSize = 14.sp,
            color = androidx.compose.ui.graphics.Color.Gray
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_sleep),
            contentDescription = "Sleep Icon",
            modifier = Modifier.size(24.dp),
            tint = androidx.compose.ui.graphics.Color(0xFF00BFA5)
        )
        Text(
            text = "${data.deepSleepMinutes}분",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = androidx.compose.ui.graphics.Color.Black
        )
        Text(
            text = "${data.lightSleepMinutes}분",
            fontSize = 14.sp,
            color = androidx.compose.ui.graphics.Color.Gray
        )
    }
}
