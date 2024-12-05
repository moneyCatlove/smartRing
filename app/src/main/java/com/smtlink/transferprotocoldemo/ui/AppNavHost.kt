package com.smtlink.transferprotocoldemo.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartring.ui.theme.components.BottomNavigationBar
import com.example.smartring.ui.theme.components.TopBar
import com.example.smartring.ui.theme.healthcare.*
import com.example.smartring.ui.theme.main.*
import com.example.smartring.ui.theme.setting.*

@Composable
fun AppNavHost(
    navController: NavController = rememberNavController(), // NavController 기본값
) {
    Scaffold(
        topBar = { TopBar(TopBarController()) },
        bottomBar = { BottomNavigationBar(navController = navController) },
        containerColor = Color(0xFFF7F7F7),
    ) { innerPadding ->
        NavHost(
            navController = navController as NavHostController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding),
        ) {
            // Main Screens
            composable("home") { MainScreen(navController = navController) }
            composable("rosary_prayer") { RosaryPrayerScreen(navController = navController) }
            composable("TodayGospelScreen") { TodayGospelScreen(navController = navController) }
            composable("HolyReadingScreen") { HolyReadingScreen(navController = navController) }
            composable("AngelusPrayerScreen") { AngelusPrayerScreen(navController = navController) }
            composable("prayer") { PrayerScreen(navController = navController) }
            composable("church_news") { ChurchNewsScreen(navController = navController) }
            composable("catholic_apps") { CatholicAppsScreen(navController = navController) }

            // HealthCare Screens
            composable("health_main") { HealthCareScreen(navController = navController) }
            composable(route = "sleep_day_detail_screen") {
                MainApplication.manager?.let { manager ->
                    val dailySleepStateController = DailySleepStateController(manager) // BLEManager로 초기화
                    SleepDayDetailScreen(
                        navController = navController,
                        controller = dailySleepStateController
                    )
                }
            }
            composable("heart_rate_detail_screen") { HeartRateDetailScreen(navController = navController, controller = HeartBeatController()) }
            composable("oxygen_level_detail_screen") { OxygenLevelDetailScreen(navController = navController) }
            composable("stress_day_detail_screen") { StressDayDetailScreen(navController = navController) }
            composable("activity_detail_screen") { ActivityDetailScreen(navController = navController) }

            // Setting Screens
            composable("find_ring_screen") {
                FindRingScreen(
                    navController = navController,
                    context = LocalContext.current, // Context를 전달
                    bleManager = BLEManager(LocalContext.current) // BLEManager를 초기화
                )
            }
            composable("health_monitoring_screen") { HealthMonitoringSettingsScreen(navController = navController) }
            composable("unit_settings_screen") { UnitSettingsScreen(navController = navController) }
            composable("low_battery_screen") { LowBatteryAlertScreen(navController = navController) }
            composable("firmware_upgrade_screen") { FirmwareUpgradeScreen(navController = navController) }
            composable("system_settings_screen") { SystemSettingsScreen(navController = navController) }
            composable("settings_screen") { SettingsScreen(navController = navController) }
        }
    }
}
