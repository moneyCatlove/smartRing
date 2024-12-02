package com.example.smartring.ui.theme.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartring.MainApplication
import com.example.smartring.R
import com.example.smartring.controller.TopBarController
import com.example.smartring.model.DeviceDataModel

@Composable
fun TopBar(controller: TopBarController) {
    val bleData = remember { mutableStateOf<DeviceDataModel?>(null) }

    val isConnected =
        MainApplication.instance
            ?.isConnectedState
            ?.collectAsState(initial = false)
            ?.value ?: false

    LaunchedEffect(isConnected) {
        if (isConnected) {
            bleData.value = controller.getInfoDevice()
        }
    }

    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
    ) {
        IconButton(
            onClick = { /* TODO: Drawer open */ },
            modifier = Modifier.align(Alignment.CenterStart),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_meun),
                contentDescription = "Menu",
                modifier = Modifier.size(28.dp),
            )
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Catholic",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
            )
            ConnectionStatus(
                isConnected = isConnected,
                batteryLevel = bleData.value?.battery_capacity,
            )
        }
    }
}
