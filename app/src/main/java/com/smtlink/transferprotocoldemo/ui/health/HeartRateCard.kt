package com.example.smartring.ui.theme.healthcare

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.smartring.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

@Composable
fun HeartRateCard(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(androidx.compose.ui.graphics.Color.White, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Column {
            // 제목과 날짜
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "심박수",
                        fontSize = 20.sp,
                        color = androidx.compose.ui.graphics.Color(0xFFe57373)
                    )
                    Text(
                        text = "2024년 8월 15일",
                        fontSize = 14.sp,
                        color = androidx.compose.ui.graphics.Color.Gray
                    )
                }
                IconButton(
                    onClick = {
                        // 심박수 상세 화면으로 이동
                        navController.navigate("heart_rate_detail_screen")
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_vector_3),
                        contentDescription = "Go to Details",
                        tint = androidx.compose.ui.graphics.Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 그래프 (MPAndroidChart를 사용)
            AndroidView(
                factory = { context ->
                    LineChart(context).apply {
                        val entries = listOf(
                            Entry(0f, 70f),
                            Entry(2f, 75f),
                            Entry(4f, 80f),
                            Entry(6f, 90f),
                            Entry(8f, 85f)
                        )
                        val dataSet = LineDataSet(entries, "심박수").apply {
                            color = Color.RED
                            valueTextColor = Color.GRAY
                            setDrawCircles(false)
                            lineWidth = 2f
                        }

                        data = LineData(dataSet)

                        // X축 설정
                        xAxis.position = XAxis.XAxisPosition.BOTTOM
                        xAxis.textColor = Color.GRAY
                        xAxis.enableGridDashedLine(10f, 10f, 0f)

                        // Y축 설정
                        axisLeft.textColor = Color.GRAY
                        axisRight.isEnabled = false

                        description.isEnabled = false
                        legend.isEnabled = false
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 심박수 상태 표시
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "❤️ 86 bpm",
                    fontSize = 16.sp,
                    color = androidx.compose.ui.graphics.Color(0xFFe57373)
                )
                Text(
                    text = "범위: 77 ~ 108 bpm",
                    fontSize = 14.sp,
                    color = androidx.compose.ui.graphics.Color.Gray
                )
            }
        }
    }
}
