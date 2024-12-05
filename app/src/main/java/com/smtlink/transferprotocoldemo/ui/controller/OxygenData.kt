package com.smtlink.transferprotocoldemo.ui.controller

class OxygenData(
    val bOxygen: Int,  // 혈중 산소 수치
    val time: String,  // 시간
    val date: String   // 날짜
) {
    override fun toString(): String {
        return "날짜: $date\n시간: $time\n산소 수치: $bOxygen%"
    }

    companion object {
        // BLE 데이터를 파싱하는 함수
        fun parseFromBLE(rawData: ByteArray): List<OxygenData> {
            val dataList = mutableListOf<OxygenData>()
            try {
                // BLE 데이터를 파싱 (예: 5바이트씩 구분하여 데이터 생성)
                for (i in rawData.indices step 5) {
                    val bOxygen = rawData[i].toInt()  // 첫 번째 바이트: 산소 수치
                    val time = "${rawData[i + 1].toInt()}:${rawData[i + 2].toInt()}" // 시간 정보
                    val date = "2024-${rawData[i + 3].toInt()}-${rawData[i + 4].toInt()}" // 날짜 정보

                    dataList.add(OxygenData(bOxygen, time, date))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return dataList
        }
    }
}
