package com.example.smartring.ui.theme.healthcare

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.smartring.MainApplication
import com.example.smartring.R
import com.example.smartring.controller.HeartBeatController
import com.example.smartring.model.HeartRateResponseModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

@Composable
fun HeartRateDetailScreen(
    navController: NavController,
    controller: HeartBeatController,
) {
    val bleData = remember { mutableStateOf<HeartRateResponseModel?>(null) }

    LaunchedEffect(Unit) {
        if (MainApplication.instance?.isConnectedState?.value == true) {
            bleData.value = controller.getData()
        }
    }

    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F7F7))
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            // 뒤로가기 버튼과 제목
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp),
                    )
                }
                Text(
                    text = "심박수",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.width(48.dp)) // 뒤로가기 버튼과 균형 맞추기
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 날짜 선택
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Previous Day",
                    tint = Color.Gray,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "2024년 5월 12일",
                    fontSize = 16.sp,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Next Day",
                    tint = Color.Gray,
                )
            }
        }

        item {
            // 그래프와 현재 심박수
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .padding(16.dp),
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    bleData.value?.array.let {
                        Text(
                            text =
                                it?.get(0)?.date ?: "데이터가 없어요",
                            fontSize = 14.sp,
                            color = Color.Gray,
                        )
                        Text(
                            text = it?.get(0)?.heart_rate ?: ("0" + "bpm"),
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    AndroidView(
                        factory = { context ->
                            LineChart(context).apply {
                                val entries =
                                    listOf(
                                        Entry(0f, 70f),
                                        Entry(1f, 75f),
                                        Entry(2f, 80f),
                                        Entry(3f, 90f),
                                        Entry(4f, 85f),
                                        Entry(5f, 100f),
                                    )
                                val dataSet =
                                    LineDataSet(entries, "심박수").apply {
                                        color = android.graphics.Color.RED
                                        valueTextColor = android.graphics.Color.TRANSPARENT
                                        lineWidth = 2f
                                        setDrawCircles(false)
                                    }
                                data = LineData(dataSet)

                                description.isEnabled = false
                                legend.isEnabled = false
                                xAxis.setDrawGridLines(false)
                                axisLeft.setDrawGridLines(false)
                                axisRight.isEnabled = false
                                xAxis.position = com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM
                            }
                        },
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                    )
                }
            }
        }

        item {
            // 심박수 통계
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .padding(16.dp),
            ) {
                InfoRow(label = "평균 심박수", value = "96 bpm")
                InfoRow(label = "최소 심박수", value = "77 bpm")
                InfoRow(label = "최대 심박수", value = "108 bpm")
            }
        }

        item {
            // 실시간 심박수 측정
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "실시간 심박수 측정",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "100 bpm",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                )
                Text(
                    text = "2024-08-19 16:48",
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* 실시간 측정 기능 */ },
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF5F5F5), // 연한 그레이 배경색
                        ),
                    shape = RoundedCornerShape(20.dp), // 모서리 반경 20dp
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(48.dp), // 버튼 높이
                ) {
                    Text(
                        text = "측정을 시작하려면 클릭하세요",
                        color = Color.Gray, // 텍스트 색상
                        fontSize = 14.sp,
                    )
                }
            }
        }

        item {
            // 실시간 측정 결과
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .padding(16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "실시간 측정 결과",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                    Text(
                        text = "더보기 >",
                        fontSize = 14.sp,
                        color = Color.Gray,
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Column {
                    listOf(
                        "87 bpm" to "16:48",
                        "93 bpm" to "16:21",
                        "100 bpm" to "16:13",
                        "97 bpm" to "15:11",
                        "104 bpm" to "14:50",
                        "87 bpm" to "14:35",
                        "87 bpm" to "12:17",
                    ).forEach { (bpm, time) ->
                        Row(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(text = "❤️ $bpm", fontSize = 14.sp, color = Color.Red)
                            Text(text = time, fontSize = 14.sp, color = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoRow(
    label: String,
    value: String,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = label, fontSize = 14.sp, color = Color.Black)
        Text(text = value, fontSize = 14.sp, color = Color.Gray)
    }
}
