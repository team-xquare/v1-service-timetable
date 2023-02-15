package com.xquare.v1servicetimetable.timetable.port.out

import com.xquare.v1servicetimetable.timetable.port.out.vo.DayTimeElementVO
import java.time.LocalDate

interface QueryTimetablePort {

    fun findTimetableEntitiesByDateAndGradeAndClassNum(
        start: LocalDate,
        end: LocalDate,
        grade: Int,
        classNum: Int
    ): Map<LocalDate, List<DayTimeElementVO>>
}
