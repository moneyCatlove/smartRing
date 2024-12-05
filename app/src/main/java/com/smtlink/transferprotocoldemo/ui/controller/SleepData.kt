package com.smtlink.transferprotocoldemo.ui.controller

class SleepData(
    val totalSleepMinutes: Int, // 총 수면 시간
    val deepSleepMinutes: Int,  // 깊은 수면 시간
    val lightSleepMinutes: Int, // 얕은 수면 시간
    val date: String            // 수면 데이터 날짜
) {
    override fun toString(): String {
        return String.format(
            "날짜: %s\n총 수면 시간: %d분\n깊은 수면: %d분\n얕은 수면: %d분",
            date, totalSleepMinutes, deepSleepMinutes, lightSleepMinutes
        )
    }
}
