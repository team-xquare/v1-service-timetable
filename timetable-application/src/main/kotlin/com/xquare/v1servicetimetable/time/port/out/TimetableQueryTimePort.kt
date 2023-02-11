package com.xquare.v1servicetimetable.time.port.out

import com.xquare.v1servicetimetable.timetable.domain.Timetable
import java.time.LocalDate

interface TimetableQueryTimePort {

    fun findTimetableEntitiesByDate(date: LocalDate): List<Timetable>
}
