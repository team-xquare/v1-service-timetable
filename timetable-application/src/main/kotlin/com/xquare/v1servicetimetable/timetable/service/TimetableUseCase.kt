package com.xquare.v1servicetimetable.timetable.service

import com.xquare.v1servicetimetable.common.annotation.UseCase
import com.xquare.v1servicetimetable.timetable.port.`in`.TimetableDrivingPort
import com.xquare.v1servicetimetable.timetable.port.`in`.dto.response.DayTimeElement
import com.xquare.v1servicetimetable.timetable.port.`in`.dto.response.WeekTimeElement
import com.xquare.v1servicetimetable.timetable.port.`in`.dto.response.WeekTimetableResponse
import com.xquare.v1servicetimetable.timetable.port.out.QueryTimetablePort
import com.xquare.v1servicetimetable.timetable.port.out.vo.DayTimeElementVO
import com.xquare.v1servicetimetable.user.port.UserPort
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.Locale

@UseCase
class TimetableUseCase(
    private val userPort: UserPort,
    private val queryTimetablePort: QueryTimetablePort
) : TimetableDrivingPort {

    override fun getWeekTimetable(): WeekTimetableResponse {
        val user = userPort.getUser(userPort.getCurrentUserId())

        val (start, end) = checkAfterFriday20hour30minutes()

        val weekTimeElement: List<WeekTimeElement> = queryTimetablePort
            .findTimetableEntitiesByDateBetweenAndGradeAndClassNum(start, end, user.grade, user.classNum)
            .map { (date, dayTimetable) ->
                WeekTimeElement(
                    weekDay = date.dayOfWeek.value,
                    date = date,
                    dayTimetable = dayTimetable.map { it.toDayTimeElement() }
                )
            }

        return WeekTimetableResponse(weekTimeElement)
    }

    private fun checkAfterFriday20hour30minutes(
    ): Pair<LocalDate, LocalDate> {
        val now = LocalDate.now()
        val nowTime = LocalTime.now()

        var start = now.with(WeekFields.of(Locale.KOREA).dayOfWeek(), 2)
        var end = now.with(WeekFields.of(Locale.KOREA).dayOfWeek(), 6)
        val isAfterThursday = now.dayOfWeek > DayOfWeek.THURSDAY
        val isAfterFriday = now.dayOfWeek > DayOfWeek.FRIDAY
        val isAfterEndTime = nowTime > LocalTime.of(20, 30)

        if (isAfterThursday && isAfterEndTime || isAfterFriday) {
            start = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
            end = now.with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
        }

        return Pair(start, end)
    }

    private fun DayTimeElementVO.toDayTimeElement(): DayTimeElement {
        return DayTimeElement(
            period = this.period,
            beginTime = this.beginTime,
            endTime = this.endTime,
            subjectName = this.subjectName,
            subjectImage = this.subjectImage
        )
    }
}
