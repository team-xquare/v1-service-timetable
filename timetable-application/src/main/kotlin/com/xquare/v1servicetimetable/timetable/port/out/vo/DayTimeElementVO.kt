package com.xquare.v1servicetimetable.timetable.port.out.vo

import java.time.LocalDate
import java.time.LocalTime

data class DayTimeElementVO(
    val period: Int,
    val beginTime: LocalTime,
    val endTime: LocalTime,
    val subjectName: String,
    val subjectImage: String,
    val date: LocalDate
)
