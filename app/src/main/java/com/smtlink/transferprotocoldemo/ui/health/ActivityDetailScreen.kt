package com.example.smartring.ui.theme.healthcare

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.smartring.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

@Composable
fun ActivityDetailScreen(navController: NavController) {
    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize()
                .background(
                    androidx.compose.ui.graphics
                        .Color(0xFFF7F7F7),
                ).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // 뒤로가기 버튼과 제목
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        tint = androidx.compose.ui.graphics.Color.Black,
                        modifier = Modifier.size(20.dp),
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "활동",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color =
                            androidx.compose.ui.graphics
                                .Color(0xFFE57373),
                    )
                    Text(
                        text = "2024년 5월 12일",
                        fontSize = 14.sp,
                        color = androidx.compose.ui.graphics.Color.Gray,
                    )
                }
            }
        }

        // 활동 목표 섹션
        item {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .background(androidx.compose.ui.graphics.Color.White, shape = RoundedCornerShape(12.dp))
                        .padding(16.dp),
            ) {
                Column {
                    Text(text = "활동 목표", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))

                    // PieChart
                    AndroidView(
                        factory = { context ->
                            PieChart(context).apply {
                                val entries =
                                    listOf(
                                        PieEntry(920f, "걸음수"),
                                        PieEntry(4.5f, "거리 (Km)"),
                                        PieEntry(150f, "칼로리 소모"),
                                    )
                                val dataSet =
                                    PieDataSet(entries, "").apply {
                                        colors =
                                            listOf(
                                                Color.parseColor("#42A5F5"), // 걸음수 색상
                                                Color.parseColor("#66BB6A"), // 거리 색상
                                                Color.parseColor("#FF7043"), // 칼로리 색상
                                            )
                                        sliceSpace = 2f
                                        setDrawValues(false)
                                    }

                                data = PieData(dataSet)
                                holeRadius = 70f
                                transparentCircleRadius = 75f
                                description.isEnabled = false
                                legend.isEnabled = false
                            }
                        },
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // 활동 정보
                    Column {
                        InfoRow(
                            label = "총 걸음수",
                            value = "920 / 10,000 걸음",
                            color =
                                androidx.compose.ui.graphics
                                    .Color(0xFF42A5F5),
                        )
                        InfoRow(
                            label = "움직인 거리",
                            value = "4.5 / 5.0 Km",
                            color =
                                androidx.compose.ui.graphics
                                    .Color(0xFF66BB6A),
                        )
                        InfoRow(
                            label = "소비 칼로리",
                            value = "150 / 300 Kcal",
                            color =
                                androidx.compose.ui.graphics
                                    .Color(0xFFFF7043),
                        )
                    }
                }
            }
        }

        // 운동 시작 섹션
        item {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .background(androidx.compose.ui.graphics.Color.White, shape = RoundedCornerShape(12.dp))
                        .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    onClick = { /* 달리기 시작 */ },
                    modifier = Modifier.weight(1f),
                    colors =
                        androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor =
                                androidx.compose.ui.graphics
                                    .Color(0xFF66BB6A),
                        ),
                ) {
                    Text(text = "달리기 시작")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = { /* 걷기 시작 */ },
                    modifier = Modifier.weight(1f),
                    colors =
                        androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor =
                                androidx.compose.ui.graphics
                                    .Color(0xFFFF7043),
                        ),
                ) {
                    Text(text = "걷기 시작")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = { /* 자전거 시작 */ },
                    modifier = Modifier.weight(1f),
                    colors =
                        androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor =
                                androidx.compose.ui.graphics
                                    .Color(0xFF42A5F5),
                        ),
                ) {
                    Text(text = "자전거 시작")
                }
            }
        }

        //
        item {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .background(androidx.compose.ui.graphics.Color.White, shape = RoundedCornerShape(12.dp))
                        .padding(16.dp),
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "80",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color =
                            androidx.compose.ui.graphics
                                .Color(0xFFE57373),
                    )
                    Text(text = "매우 좋음", fontSize = 16.sp, color = androidx.compose.ui.graphics.Color.Gray)

                    Spacer(modifier = Modifier.height(16.dp))

                    InfoRow(
                        label = "활동 시간",
                        value = "30분",
                        color =
                            androidx.compose.ui.graphics
                                .Color(0xFF66BB6A),
                    )
                    InfoRow(
                        label = "유용한 활동",
                        value = "평균",
                        color =
                            androidx.compose.ui.graphics
                                .Color(0xFF42A5F5),
                    )
                    InfoRow(
                        label = "활동량",
                        value = "적당함",
                        color =
                            androidx.compose.ui.graphics
                                .Color(0xFFFF7043),
                    )
                }
            }
        }
    }
}

@Composable
fun InfoRow(
    label: String,
    value: String,
    color: androidx.compose.ui.graphics.Color,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = label, fontSize = 14.sp, color = androidx.compose.ui.graphics.Color.Black)
        Text(text = value, fontSize = 14.sp, color = color)
    }
}
