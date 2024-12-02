package com.example.smartring.ui.theme.healthcare

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.smartring.R
import com.example.smartring.controller.DailySleepController
import com.example.smartring.model.DailySleepModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

@Composable
fun SleepCard(navController: NavController, controller: DailySleepController) {
    var sleepData by remember { mutableStateOf<DailySleepModel?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // 데이터 가져오는
    LaunchedEffect(Unit) {
        controller.fetchDailySleepData { data ->
            sleepData = data
            isLoading = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(androidx.compose.ui.graphics.Color.White, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        if (isLoading) {
            Text(
                text = "데이터 로딩 중...",
                modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
            )
        } else {
            Column {
                // 제목과 날짜
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
                            text = sleepData?.array?.firstOrNull()?.date ?: "날짜 없음",
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

                Spacer(modifier = Modifier.height(16.dp))

                AndroidView(
                    factory = { context ->
                        PieChart(context).apply {
                            val deepSleep = sleepData?.array?.firstOrNull()?.deepSleep?.toFloatOrNull() ?: 0f
                            val lightSleep = sleepData?.array?.firstOrNull()?.lightSleep?.toFloatOrNull() ?: 0f
                            val totalSleep = deepSleep + lightSleep
                            val entries = listOf(
                                PieEntry(deepSleep, "깊은 수면"),
                                PieEntry(lightSleep, "얕은 수면")
                            )
                            val dataSet = PieDataSet(entries, "").apply {
                                colors = listOf(
                                    Color.parseColor("#00BFA5"),
                                    Color.parseColor("#E0E0E0")
                                )
                                sliceSpace = 2f
                                setDrawValues(false)
                            }
                            data = PieData(dataSet)

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

                Spacer(modifier = Modifier.height(8.dp))

                // 중앙 점수 및 상태
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val totalSleepTime = "${sleepData?.array?.firstOrNull()?.deepSleep ?: "0시간"} " +
                            "+ ${sleepData?.array?.firstOrNull()?.lightSleep ?: "0분"}"
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

                Spacer(modifier = Modifier.height(16.dp))

                // 하단 정보
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = sleepData?.array?.firstOrNull()?.date ?: "시간 없음",
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
                        text = sleepData?.array?.firstOrNull()?.deepSleep ?: "0시간 0분",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = androidx.compose.ui.graphics.Color.Black
                    )
                    Text(
                        text = sleepData?.array?.firstOrNull()?.lightSleep ?: "0시간 0분",
                        fontSize = 14.sp,
                        color = androidx.compose.ui.graphics.Color.Gray
                    )
                }
            }
        }
    }
}
