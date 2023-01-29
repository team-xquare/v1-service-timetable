package com.xquare.v1servicetimetable.domain

import com.xquare.v1servicetimetable.common.annotation.Aggregate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

@Aggregate
class Timetable(
    val id: UUID,
    val period: Int,
    val beginTime: LocalTime,
    val endTime: LocalTime,
    val weekDay: Int,
    val date: LocalDate,
    val grade: Int,
    val classNumber: Int,
    val type: TableType,
    val subjectId: UUID
)
