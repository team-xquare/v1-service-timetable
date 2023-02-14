package com.xquare.v1servicetimetable.timetable.port.out

import com.xquare.v1servicetimetable.timetable.port.`in`.dto.response.DayTimeElement
import java.time.LocalDate

interface QueryTimetablePort {

    fun findTimetableEntitiesByDateAndGradeAndClassNum(
        start: LocalDate,
        end: LocalDate,
        grade: Int,
        classNum: Int
    ): Map<LocalDate, List<DayTimeElement>>
}
