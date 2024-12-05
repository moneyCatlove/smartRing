package com.example.smartring.ui.theme.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.smtlink.transferprotocoldemo.R

@Composable
fun CatholicAppsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "가톨릭 추천앱",
                fontSize = 24.sp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 추천 앱 목록
        val apps = listOf(
            AppData(R.drawable.ic_goodnews, "굿뉴스 / Goodnews", "천주교 서울대교구에서 운영하는 가톨릭 선교 및 복음화를 위한 굿뉴스 모바일 어플리케이션입니다."),
            AppData(R.drawable.ic_daily, "매일미사", "천주교에서 매일 봉헌되는 미사의 독서와 복음을 전례력에 맞추어 보실 수 있는 가톨릭 어플리케이션입니다."),
            AppData(R.drawable.ic_catholicbible, "가톨릭 성경", "한국 천주교 주교회의에서 채용한 가톨릭 공용 성경을 담고 있습니다."),
            AppData(R.drawable.ic_catholichymns, "가톨릭 성가", "가톨릭(Catholic) 성인 성가집. 3G, WiFi 네트워크 연결 되지 않은 상태에서도 무료로 완벽 사용 가능.")
        )

        Column {
            apps.forEach { app ->
                AppCard(app)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun AppCard(appData: AppData) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = appData.icon),
                contentDescription = appData.title,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = appData.title, fontSize = 18.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = appData.description, fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

// 추천 앱 데이터 클래스
data class AppData(
    val icon: Int,
    val title: String,
    val description: String
)
