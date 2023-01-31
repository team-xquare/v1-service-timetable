package com.xquare.v1servicetimetable.time.port.out

import com.xquare.v1servicetimetable.time.domain.Time
import java.time.LocalDate

interface QueryTimePort {

    fun queryTime(date: LocalDate): List<Time>
}
