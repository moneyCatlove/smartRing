package com.example.smartring.ui.theme.healthcare

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.smtlink.transferprotocoldemo.R
import com.smtlink.transferprotocoldemo.ui.controller.OxygenData

@Composable
fun OxygenLevelCard(navController: NavController, controller: OxygenData.Companion) {
    var oxygenData by remember { mutableStateOf<List<OxygenData>?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // 데이터를 로드
    LaunchedEffect(Unit) {
        controller.fetchOxygenData { data: List<OxygenData> ->
            oxygenData = data
            isLoading = false
        }

    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(androidx.compose.ui.graphics.Color.White, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        when {
            isLoading -> {
                // 로딩 중
                Text(
                    text = "데이터 로딩 중...",
                    fontSize = 16.sp,
                    color = androidx.compose.ui.graphics.Color.Gray
                )
            }
            oxygenData.isNullOrEmpty() -> {
                // 데이터가 없는 경우
                Text(
                    text = "데이터를 가져올 수 없습니다.",
                    fontSize = 16.sp,
                    color = androidx.compose.ui.graphics.Color.Red
                )
            }
            else -> {
                // 데이터가 있는 경우
                val data = oxygenData!!
                Column {
                    // 헤더
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text(
                                text = "혈중 산소",
                                fontSize = 20.sp,
                                color = androidx.compose.ui.graphics.Color(0xFF42A5F5)
                            )
                            Text(
                                text = data.first().date,
                                fontSize = 14.sp,
                                color = androidx.compose.ui.graphics.Color.Gray
                            )
                        }
                        IconButton(
                            onClick = { navController.navigate("oxygen_level_detail_screen") }
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

                    // 그래프
                    AndroidView(
                        factory = { context ->
                            BarChart(context).apply {
                                val entries = data.mapIndexed { index, model ->
                                    BarEntry(index.toFloat(), model.bOxygen.toFloat())
                                }

                                val dataSet = BarDataSet(entries, "혈중 산소").apply {
                                    color = Color.parseColor("#42A5F5")
                                    valueTextColor = Color.GRAY
                                    valueTextSize = 10f
                                    setGradientColor(Color.parseColor("#42A5F5"), Color.parseColor("#B3E5FC"))
                                }

                                this.data = BarData(dataSet)

                                // X축 설정
                                xAxis.apply {
                                    position = XAxis.XAxisPosition.BOTTOM
                                    textColor = Color.GRAY
                                    setDrawGridLines(false)
                                    granularity = 1f
                                    labelCount = data.size
                                    valueFormatter = object : IndexAxisValueFormatter() {
                                        override fun getFormattedValue(value: Float): String {
                                            return data.getOrNull(value.toInt())?.time ?: ""
                                        }
                                    }
                                }

                                // Y축 설정
                                axisLeft.apply {
                                    textColor = Color.GRAY
                                    axisMinimum = 0f
                                    axisMaximum = 100f
                                }
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

                    // 산소 상태
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "\uD83D\uDCA7 ${data.last().bOxygen} %",
                            fontSize = 16.sp,
                            color = androidx.compose.ui.graphics.Color(0xFF42A5F5)
                        )
                        Text(
                            text = "범위: 90 ~ 100 %",
                            fontSize = 14.sp,
                            color = androidx.compose.ui.graphics.Color.Gray
                        )
                    }
                }
            }
        }
    }
}
