package com.example.smartring.ui.theme.healthcare

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.smartring.R
import com.example.smartring.model.BloodOxygenModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

@Composable
fun OxygenLevelCard(navController: NavController, fetchData: (onResult: (List<BloodOxygenModel>?) -> Unit) -> Unit) {
    var oxygenData by remember { mutableStateOf<List<BloodOxygenModel>?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // Fetch data
    LaunchedEffect(Unit) {
        fetchData { data ->
            oxygenData = data
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
            // Loading 상태 표시
            Text(text = "데이터 로딩 중...", color = androidx.compose.ui.graphics.Color.Gray, fontSize = 14.sp)
        } else if (oxygenData.isNullOrEmpty()) {
            // 데이터가 없는 경우
            Text(text = "데이터를 가져올 수 없습니다.", color = androidx.compose.ui.graphics.Color.Red, fontSize = 14.sp)
        } else {
            Column {
                // 제목과 날짜
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
                            text = oxygenData?.firstOrNull()?.date ?: "날짜 정보 없음",
                            fontSize = 14.sp,
                            color = androidx.compose.ui.graphics.Color.Gray
                        )
                    }
                    IconButton(
                        onClick = {
                            navController.navigate("oxygen_level_detail_screen")
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
                            // 실제 데이터 반영
                            val entries = oxygenData?.mapIndexed { index, data ->
                                BarEntry(index.toFloat(), data.bOxygen.toFloat())
                            } ?: emptyList()

                            val dataSet = BarDataSet(entries, "혈중 산소").apply {
                                color = Color.parseColor("#42A5F5")
                                valueTextColor = Color.GRAY
                                valueTextSize = 10f
                                setGradientColor(Color.parseColor("#42A5F5"), Color.parseColor("#B3E5FC"))
                            }

                            val barData = BarData(dataSet)
                            data = barData

                            // X축 설정
                            xAxis.position = XAxis.XAxisPosition.BOTTOM
                            xAxis.textColor = Color.GRAY
                            xAxis.setDrawGridLines(false)
                            xAxis.granularity = 1f
                            xAxis.labelCount = oxygenData?.size ?: 0
                            xAxis.valueFormatter = object : IndexAxisValueFormatter() {
                                override fun getFormattedValue(value: Float): String {
                                    return oxygenData?.getOrNull(value.toInt())?.time ?: ""
                                }
                            }

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

                // 혈중 산소 상태 표시
                val latestOxygen = oxygenData?.lastOrNull()?.bOxygen ?: 0
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "\uD83D\uDCA7 $latestOxygen %",
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
