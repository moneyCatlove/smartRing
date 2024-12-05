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
import com.smtlink.transferprotocoldemo.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

@Composable
fun StressCard(navController: NavController) {
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
                        text = "스트레스",
                        fontSize = 20.sp,
                        color = androidx.compose.ui.graphics.Color(0xFF66BB6A)
                    )
                    Text(
                        text = "2024년 8월 15일",
                        fontSize = 14.sp,
                        color = androidx.compose.ui.graphics.Color.Gray
                    )
                }
                IconButton(
                    onClick = {
                        navController.navigate("stress_day_detail_screen")
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
                    BarChart(context).apply {
                        // Dummy Data
                        val entries = listOf(
                            BarEntry(0f, 40f),
                            BarEntry(1f, 45f),
                            BarEntry(2f, 30f),
                            BarEntry(3f, 50f),
                            BarEntry(4f, 35f),
                            BarEntry(5f, 40f)
                        )
                        val dataSet = BarDataSet(entries, "스트레스").apply {
                            color = Color.parseColor("#66BB6A")
                            valueTextColor = Color.GRAY
                            valueTextSize = 10f
                            setGradientColor(Color.parseColor("#66BB6A"), Color.parseColor("#B2FF59"))
                        }

                        val barData = BarData(dataSet)
                        data = barData

                        // X축 설정
                        xAxis.position = XAxis.XAxisPosition.BOTTOM
                        xAxis.textColor = Color.GRAY
                        xAxis.setDrawGridLines(false)
                        xAxis.granularity = 1f

                        // Y축 설정
                        axisLeft.textColor = Color.GRAY
                        axisLeft.axisMinimum = 0f
                        axisLeft.axisMaximum = 100f
                        axisRight.isEnabled = false

                        description.isEnabled = false
                        legend.isEnabled = false

                        setFitBars(true)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 스트레스 상태 표시
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "\uD83C\uDF31 45 %",
                    fontSize = 16.sp,
                    color = androidx.compose.ui.graphics.Color(0xFF66BB6A)
                )
                Text(
                    text = "범위: 30 ~ 49 %",
                    fontSize = 14.sp,
                    color = androidx.compose.ui.graphics.Color.Gray
                )
            }
        }
    }
}
