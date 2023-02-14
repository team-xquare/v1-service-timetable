package com.xquare.v1servicetimetable.timetable.service

import com.xquare.v1servicetimetable.common.annotation.UseCase
import com.xquare.v1servicetimetable.timetable.port.`in`.TimetableDrivingPort
import com.xquare.v1servicetimetable.timetable.port.`in`.dto.response.WeekTimeElement
import com.xquare.v1servicetimetable.timetable.port.`in`.dto.response.WeekTimetableResponse
import com.xquare.v1servicetimetable.timetable.port.out.QueryTimetablePort
import com.xquare.v1servicetimetable.user.port.UserPort
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*

@UseCase
class TimetableUseCase(
    private val userPort: UserPort,
    private val queryTimetablePort: QueryTimetablePort
) : TimetableDrivingPort {

    override fun getWeekTimetable(): WeekTimetableResponse {
        val user = userPort.getUser(userPort.getCurrentUserId())
        val start = LocalDate.now().with(WeekFields.of(Locale.KOREA).dayOfWeek(), 2)
        val end = LocalDate.now().with(WeekFields.of(Locale.KOREA).dayOfWeek(), 6)

        val weekTimeElement: List<WeekTimeElement> = queryTimetablePort
            .findTimetableEntitiesByDateAndGradeAndClassNum(start, end, user.grade, user.classNum)
            .map {
                WeekTimeElement(
                    weekDay = it.key.dayOfWeek.value,
                    dayTimetable = it.value
                )
            }

        return WeekTimetableResponse(weekTimeElement)
    }
}
