package com.craft.projectx.utils

import com.craft.projectx.data.UsageData


object DummyUsage {

    val dummyUsage = generateDummyUsage()

    private fun generateDummyUsage(): List<UsageData> {
        val usageList = mutableListOf<UsageData>()

        val usage1 = UsageData(
            appName = "Instagram",
            time = 23,
            timeUsed = 0.7f
        )
        val usage2 = UsageData(
            appName = "Snapchat",
            time = 7,
            timeUsed = 0.3f
        )
        usageList.add(usage1)
        usageList.add(usage2)


        return usageList
    }
}