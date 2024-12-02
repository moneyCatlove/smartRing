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
import com.example.smartring.model.BloodOxygenModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.example.smartring.ui.theme.components.InfoRow

@Composable
fun OxygenLevelDetailScreen(
    navController: NavController,
) {
    var oxygenData by remember { mutableStateOf<List<BloodOxygenModel>?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // Fetch data
    LaunchedEffect(Unit) {
//        fetchData { data ->
//            oxygenData = data
//            isLoading = false
//        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(androidx.compose.ui.graphics.Color(0xFFF7F7F7))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 뒤로가기 및 상단 제목
        item {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = androidx.compose.ui.graphics.Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "혈중 산소", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(
                text = oxygenData?.firstOrNull()?.date ?: "날짜 정보 없음",
                fontSize = 14.sp,
                color = androidx.compose.ui.graphics.Color.Gray
            )
        }

        // BarChart와 데이터
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(androidx.compose.ui.graphics.Color.White, shape = RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                if (isLoading) {
                    Text(text = "데이터 로딩 중...", color = androidx.compose.ui.graphics.Color.Gray, fontSize = 14.sp)
                } else if (oxygenData.isNullOrEmpty()) {
                    Text(text = "데이터를 가져올 수 없습니다.", color = androidx.compose.ui.graphics.Color.Red, fontSize = 14.sp)
                } else {
                    Column {
                        Text(
                            text = oxygenData?.firstOrNull()?.time ?: "시간 정보 없음",
                            fontSize = 14.sp,
                            color = androidx.compose.ui.graphics.Color.Gray
                        )
                        Text(
                            text = "${oxygenData?.firstOrNull()?.bOxygen ?: 0} %",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            color = androidx.compose.ui.graphics.Color(0xFF42A5F5)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // BarChart
                        AndroidView(
                            factory = { context ->
                                BarChart(context).apply {
                                    val entries = oxygenData?.mapIndexed { index, data ->
                                        BarEntry(index.toFloat(), data.bOxygen.toFloat())
                                    } ?: emptyList()

                                    val dataSet = BarDataSet(entries, "혈중 산소").apply {
                                        color = Color.parseColor("#42A5F5")
                                        valueTextColor = Color.TRANSPARENT
                                        valueTextSize = 10f
                                    }

                                    val barData = BarData(dataSet)
                                    data = barData

                                    xAxis.position = XAxis.XAxisPosition.BOTTOM
                                    xAxis.textColor = Color.GRAY
                                    xAxis.setDrawGridLines(false)
                                    xAxis.valueFormatter = IndexAxisValueFormatter(
                                        oxygenData?.map { it.time } ?: emptyList()
                                    )

                                    axisLeft.textColor = Color.GRAY
                                    axisLeft.axisMinimum = 0f
                                    axisLeft.axisMaximum = 100f
                                    axisRight.isEnabled = false

                                    description.isEnabled = false
                                    legend.isEnabled = false
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }
                }
            }
        }

        // 평균, 최소, 최대 데이터
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(androidx.compose.ui.graphics.Color.White, shape = RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                val average = oxygenData?.map { it.bOxygen }?.average()?.toInt() ?: 0
                val min = oxygenData?.minOfOrNull { it.bOxygen } ?: 0
                val max = oxygenData?.maxOfOrNull { it.bOxygen } ?: 0

                InfoRow(label = "평균 혈중 산소", value = "$average %")
                InfoRow(label = "최소 혈중 산소", value = "$min %")
                InfoRow(label = "최대 혈중 산소", value = "$max %")
            }
        }

        // 실시간 혈중 산소 측정
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(androidx.compose.ui.graphics.Color.White, shape = RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "실시간 혈중 산소 측정", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = "${oxygenData?.lastOrNull()?.bOxygen ?: 0} %",
                        fontSize = 16.sp,
                        color = androidx.compose.ui.graphics.Color(0xFF42A5F5)
                    )
                }
                Text(
                    text = oxygenData?.lastOrNull()?.time ?: "시간 정보 없음",
                    fontSize = 14.sp,
                    color = androidx.compose.ui.graphics.Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { /* 실시간 측정 */ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = androidx.compose.ui.graphics.Color(0xFFF5F5F5)
                    )
                ) {
                    Text(text = "측정을 시작하려면 클릭하세요", color = androidx.compose.ui.graphics.Color.Gray)
                }
            }
        }
    }
}
