package com.xquare.v1servicetimetable.adapter.out.thirdparty.feign.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "NeisClient", url = "\${neis.url}")
interface NeisClient {

    @GetMapping("/SchoolSchedule")
    fun getSchoolSchedule(
        @RequestParam("KEY") key: String,
        @RequestParam("Type") type: String,
        @RequestParam("pSize") pSize: Int,
        @RequestParam("ATPT_OFCDC_SC_CODE") regionCode: String,
        @RequestParam("SD_SCHUL_CODE") schoolCode: String,
        @RequestParam("AA_FROM_YMD") startDate: String
    ): String

    @GetMapping("/hisTimetable")
    fun getTimetable(
        @RequestParam("KEY") key: String,
        @RequestParam("Type") type: String,
        @RequestParam("pSize") pSize: Int,
        @RequestParam("ATPT_OFCDC_SC_CODE") regionCode: String,
        @RequestParam("SD_SCHUL_CODE") schoolCode: String,
        @RequestParam("AY") year: String,
        @RequestParam("SEM") semester: String,
        @RequestParam("GRADE") grade: String,
        @RequestParam("CLASS_NM") `class`: String,
        @RequestParam("TI_FROM_YMD") startDate: String,
        @RequestParam("TI_TO_YMD") endDate: String
    ): String
}
