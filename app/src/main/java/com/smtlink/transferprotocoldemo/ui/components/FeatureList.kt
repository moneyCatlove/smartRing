package com.example.smartring.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartring.R

@Composable
fun FeatureList(navController: NavController, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(10.dp),
        contentPadding = PaddingValues(bottom = 56.dp) 
    ) {
        item {
            FeatureItem(
                title = "묵주기도",
                subtitle = "묵주기도 단수 기록",
                primaryIconId = R.drawable.ic_vector_3,
                secondaryIconId = R.drawable.ic_hands
            ) {
                navController.navigate("rosary_prayer")
            }
        }
        item {
            FeatureItem(
                title = "오늘의 복음",
                subtitle = "매일미사 중 복음말씀",
                primaryIconId = R.drawable.ic_vector_3
            ) {
                navController.navigate("TodayGospelScreen")
            }
        }
        item {
            FeatureItem(
                title = "거룩한 독서",
                subtitle = "연중시기에 따른 주별 동영상",
                primaryIconId = R.drawable.ic_vector_3
            ) {
                navController.navigate("HolyReadingScreen")
            }
        }
        item {
            FeatureItem(
                title = "삼종기도",
                subtitle = "2024년 8월 15일",
                primaryIconId = R.drawable.ic_vector_3
            ) {
                navController.navigate("AngelusPrayerScreen")
            }
        }
        item {
            FeatureItem(
                title = "기도문",
                subtitle = "가톨릭 기도문 검색",
                primaryIconId = R.drawable.ic_vector_3
            ) {
                navController.navigate("prayer")
            }
        }
        item {
            FeatureItem(
                title = "성당소식",
                subtitle = "성당 소식 및 행사 안내",
                primaryIconId = R.drawable.ic_vector_3
            ) {
                navController.navigate("church_news")
            }
        }
        item {
            FeatureItem(
                title = "가톨릭 추천앱",
                subtitle = "가톨릭 대표 추천앱",
                primaryIconId = R.drawable.ic_vector_3
            ) {
                navController.navigate("catholic_apps")
            }
        }
    }
}

@Composable
fun FeatureItem(
    title: String,
    subtitle: String,
    primaryIconId: Int,
    secondaryIconId: Int? = null, // 서브 아이콘은 기본값이 null
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween // 요소 간 간격 조정
    ) {
        // 텍스트 컬럼
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = title, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = subtitle, fontSize = 14.sp, color = Color.Gray)
        }

        // 서브 아이콘 (왼쪽)
        secondaryIconId?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = "$title Secondary Icon",
                modifier = Modifier
                    .size(110.dp)
                    .padding(end = 8.dp), // 첫 번째 아이콘과 간격 추가
                tint = Color.Unspecified // 아이콘의 원래 색깔 유지
            )
        }

        // 첫 번째 아이콘 (오른쪽)
        Icon(
            painter = painterResource(id = primaryIconId),
            contentDescription = "$title Primary Icon",
            modifier = Modifier.size(20.dp),
            tint = Color.Unspecified // 아이콘의 원래 색깔 유지
        )
    }
}
