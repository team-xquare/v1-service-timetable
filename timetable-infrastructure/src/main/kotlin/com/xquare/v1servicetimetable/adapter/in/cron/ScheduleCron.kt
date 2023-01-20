package com.xquare.v1servicetimetable.adapter.`in`.cron

import com.xquare.v1servicetimetable.adapter.`in`.cron.dto.ScheduleElement
import com.xquare.v1servicetimetable.adapter.`in`.cron.properties.NeisProperties
import com.xquare.v1servicetimetable.adapter.out.thirdparty.feign.client.NeisClient
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ScheduleCron(
    private val neisClient: NeisClient,
    private val neisProperties: NeisProperties
) {
    companion object {
        const val SCHOOL_SCHEDULE = "SchoolSchedule"
        const val ROW = "row"
        const val DATE_FORMAT = "yyyyMMdd"
        const val NAME = "EVENT_NM"
        const val DATE = "AA_YMD"
    }

    fun scheduleCron(): List<ScheduleElement> {
        val scheduleValue: String = neisClient.getSchoolSchedule(
            key = neisProperties.key,
            type = neisProperties.type,
            pSize = neisProperties.size,
            regionCode = neisProperties.region,
            schoolCode = neisProperties.school,
            startDate = LocalDate.of(LocalDate.now().year, 3, 1)
                .format(DateTimeFormatter.ofPattern(DATE_FORMAT))
        )

        return dataProcessing(scheduleValue)
    }

    private fun dataProcessing(data: String): List<ScheduleElement> =
        JSONObject(data)
            .getJSONArray(SCHOOL_SCHEDULE).get(1)
            .let {
                it as JSONObject
                it.get(ROW)
            }
            .let {
                it as JSONArray
            }
            .map {
                it as JSONObject
            }
            .map {
                ScheduleElement(
                    date = dateConverter(it.get(DATE).toString()),
                    name = it.get(NAME).toString()
                )
            }

    private fun dateConverter(date: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
        return LocalDate.parse(date, formatter)
    }
}
