package com.xquare.v1servicetimetable.timetable.port.`in`.dto.response

import java.time.LocalDate
import java.time.LocalTime

data class WeekTimetableResponse(
    val weekTimetable: List<WeekTimeElement>
)

data class WeekTimeElement(
    val weekDay: Int,
    val dayTimetable: List<DayTimeElement>
)

data class DayTimeElement(
    val period: Int,
    val beginTime: LocalTime,
    val endTime: LocalTime,
    val subjectName: String,
    val subjectImage: String,
    val date: LocalDate
)
