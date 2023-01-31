package com.xquare.v1servicetimetable.time.port.`in`.dto.response

import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

data class TimeResponse(
    val date: LocalDate,
    val times: List<TimeElement>
) {
    data class TimeElement(
        val id: UUID,
        val period: Int,
        val beginTime: LocalTime,
        val endTime: LocalTime
    )
}
