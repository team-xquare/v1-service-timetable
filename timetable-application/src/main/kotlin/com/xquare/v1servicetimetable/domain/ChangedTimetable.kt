package com.xquare.v1servicetimetable.domain

import com.xquare.v1servicetimetable.common.annotation.Aggregate
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Aggregate
class ChangedTimetable(
    val id: UUID,
    val period: Int,
    val beginTime: LocalDateTime,
    val endTime: LocalDateTime,
    val weekDay: Int,
    val date: LocalDate,
    val grade: Int,
    val `class`: Int,
    val subjectId: UUID
)
