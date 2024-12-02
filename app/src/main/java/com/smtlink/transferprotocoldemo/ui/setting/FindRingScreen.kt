package com.example.smartring.ui.theme.setting

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.example.smartring.ble.BLEManager

@Composable
fun FindRingScreen(
    navController: NavController,
    context: Context,
    bleManager: BLEManager // BLEManager를 전달받아 사용
) {
    var isScanning by remember { mutableStateOf(false) }
    var foundDevices by remember { mutableStateOf(listOf<BluetoothDevice>()) }
    var selectedDevice by remember { mutableStateOf<BluetoothDevice?>(null) }
    var connectionStatus by remember { mutableStateOf("Not Connected") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "반지 찾기 화면입니다.")

        Spacer(modifier = Modifier.height(16.dp))

        // BLE 스캔 버튼
        Button(
            onClick = {
                if (!isScanning) {
                    if (ActivityCompat.checkSelfPermission(
                            context,
                            Manifest.permission.BLUETOOTH_SCAN
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        Log.e("FindRingScreen", "Missing BLUETOOTH_SCAN permission")
                        return@Button
                    }

                    isScanning = true
                    foundDevices = listOf() // 이전 스캔 결과 초기화

                    bleManager.startScanning { devices ->
                        isScanning = false
                        foundDevices = devices
                        Log.d("FindRingScreen", "Found devices: ${devices.size}")
                    }
                }
            }
        ) {
            Text(if (isScanning) "Scanning..." else "Start Scan")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 발견된 기기 표시
        if (foundDevices.isNotEmpty()) {
            Text(text = "Found Devices:")
            Spacer(modifier = Modifier.height(8.dp))
            foundDevices.forEach { device ->
                Button(
                    onClick = {
                        selectedDevice = device
                        if (ActivityCompat.checkSelfPermission(
                                context,
                                Manifest.permission.BLUETOOTH_CONNECT
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            Log.e("FindRingScreen", "Missing BLUETOOTH_CONNECT permission")
                            return@Button
                        }

                        Log.d("FindRingScreen", "Connecting to ${device.name} (${device.address})")
                        connectionStatus = "Connecting to ${device.name ?: "Unnamed"}..."

                        val gatt = bleManager.connectToDevice(device.address)
                        connectionStatus = if (gatt != null) {
                            "Connected to ${device.name ?: "Unnamed"}"
                        } else {
                            "Connection failed to ${device.name ?: "Unnamed"}"
                        }
                    },
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Text(text = "${device.name ?: "Unnamed"} (${device.address})")
                }
            }
        }

        // 선택된 기기 및 연결 상태 표시
        if (selectedDevice != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Selected Device: ${selectedDevice?.name} (${selectedDevice?.address})")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Connection Status: $connectionStatus")
        }
    }
}
