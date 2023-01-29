package com.xquare.v1servicetimetable.adapter.`in`.cron

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.xquare.v1servicetimetable.adapter.`in`.cron.dto.TimetableElement
import com.xquare.v1servicetimetable.adapter.`in`.cron.properties.NeisProperties
import com.xquare.v1servicetimetable.adapter.out.thirdparty.feign.client.NeisClient
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@Component
class TimetableCron(
    private val neisClient: NeisClient,
    private val neisProperties: NeisProperties
) {
    companion object {
        const val HIS_TIMETABLE = "hisTimetable"
        const val ROW = "row"
        const val DATE_FORMAT = "yyyyMMdd"
        const val NAME = "ITRT_CNTNT"
        const val DATE = "ALL_TI_YMD"
        const val PERIOD = "PERIO"
        const val MONDAY = "MONDAY"
        const val FRIDAY = "FRIDAY"
    }

    fun timetableCron(grade: String, `class`: String): List<TimetableElement> {
        val semester: String = if (LocalDate.now().monthValue < 7) "1" else "2"

        val timetableValue: String = neisClient.getTimetable(
            key = neisProperties.key,
            type = neisProperties.type,
            pSize = neisProperties.size,
            regionCode = neisProperties.region,
            schoolCode = neisProperties.school,
            year = "${LocalDate.now().year}",
            semester = semester,
            grade = grade,
            `class` = `class`,
            startDate = getMondayOrFridayDate(MONDAY).format(DateTimeFormatter.ofPattern(DATE_FORMAT)),
            endDate = getMondayOrFridayDate(FRIDAY).format(DateTimeFormatter.ofPattern(DATE_FORMAT))
        )

        return dataProcessing(timetableValue)
    }

    private fun getMondayOrFridayDate(type: String): LocalDate {
        // TODO: cron 세부날짜 정하기
        val date: LocalDate = if (LocalDate.now().monthValue < 7) {
            LocalDate.of(LocalDate.now().year, 3, 14)
        } else {
            LocalDate.of(LocalDate.now().year, 8, 8)
        }

        val dateNumber: Int = date.dayOfWeek.value

        return if (type == MONDAY) {
            date.minusDays(dateNumber.toLong() - 1)
        } else {
            if (dateNumber <= 5) {
                date.plusDays(5 - dateNumber.toLong())
            } else {
                date.minusDays(7 - dateNumber.toLong())
            }
        }
    }

    private fun dataProcessing(data: String): List<TimetableElement> =
        jacksonObjectMapper().readValue<JsonNode>(data)
            .findValue(HIS_TIMETABLE)
            .findValue(ROW)
            .map {
                TimetableElement(
                    date = dateConverter(it.get(DATE).asText()),
                    period = it.get(PERIOD).asText().toInt(),
                    subject = it.get(NAME).asText()
                )
            }

    private fun dateConverter(date: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
        return LocalDate.parse(date, formatter)
    }

    private fun getDayOfWeek(date: LocalDate): String {
        return date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREA)
    }
}
