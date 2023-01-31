package com.xquare.v1servicetimetable.time.port.`in`

import com.xquare.v1servicetimetable.time.port.`in`.dto.response.TimeResponse
import java.time.LocalDate

interface TimeDrivingPort {

    fun getTime(date: LocalDate): TimeResponse
}
