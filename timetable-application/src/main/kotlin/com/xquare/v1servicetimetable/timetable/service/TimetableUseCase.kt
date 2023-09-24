package com.xquare.v1servicetimetable.timetable.service

import com.xquare.v1servicetimetable.common.annotation.UseCase
import com.xquare.v1servicetimetable.timetable.port.`in`.TimetableDrivingPort
import com.xquare.v1servicetimetable.timetable.port.`in`.dto.response.DayTimeElement
import com.xquare.v1servicetimetable.timetable.port.`in`.dto.response.WeekTimeElement
import com.xquare.v1servicetimetable.timetable.port.`in`.dto.response.WeekTimetableResponse
import com.xquare.v1servicetimetable.timetable.port.out.QueryTimetablePort
import com.xquare.v1servicetimetable.timetable.port.out.vo.DayTimeElementVO
import com.xquare.v1servicetimetable.user.port.UserPort
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale

@UseCase
class TimetableUseCase(
    private val userPort: UserPort,
    private val queryTimetablePort: QueryTimetablePort
) : TimetableDrivingPort {

    override fun getWeekTimetable(): WeekTimetableResponse {
        val user = userPort.getUser(userPort.getCurrentUserId())
        val start = LocalDate.now().with(WeekFields.of(Locale.KOREA).dayOfWeek(), 2)
        val end = LocalDate.now().with(WeekFields.of(Locale.KOREA).dayOfWeek(), 6)

        val weekTimeElement = queryTimetablePort
            .findTimetableEntitiesByDateBetweenAndGradeAndClassNum(
                start = start,
                end = end,
                grade = user.grade,
                classNum = user.classNum,
            )
            .map { (date, dayTimetable) ->
                WeekTimeElement(
                    weekDay = date.dayOfWeek.value,
                    date = date,
                    dayTimetable = dayTimetable.map { it.toDayTimeElement() }
                )
            }

        return WeekTimetableResponse(weekTimeElement)
    }

    private fun DayTimeElementVO.toDayTimeElement() = DayTimeElement(
        period = this.period,
        beginTime = this.beginTime,
        endTime = this.endTime,
        subjectName = this.subjectName,
        subjectImage = this.subjectImage,
    )
}
