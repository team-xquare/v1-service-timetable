package com.xquare.v1servicetimetable.time.port.out

import com.xquare.v1servicetimetable.time.domain.Time
import java.time.LocalDate

interface QueryTimePort {

    fun findTimeEntitiesByDate(date: LocalDate): List<Time>
}
