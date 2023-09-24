package com.xquare.v1servicetimetable.time.service

import com.xquare.v1servicetimetable.common.annotation.UseCase
import com.xquare.v1servicetimetable.common.enums.PeriodType
import com.xquare.v1servicetimetable.time.domain.Time
import com.xquare.v1servicetimetable.time.port.`in`.TimeDrivingPort
import com.xquare.v1servicetimetable.time.port.`in`.dto.response.TimeResponse
import com.xquare.v1servicetimetable.time.port.`in`.dto.response.TimeResponse.TimeElement
import com.xquare.v1servicetimetable.time.port.out.QueryTimePort
import com.xquare.v1servicetimetable.time.port.out.TimetableQueryTimePort
import java.time.LocalDate

@UseCase
class TimeUseCase(
    private val queryTimePort: QueryTimePort,
    private val timetableQueryTimePort: TimetableQueryTimePort
) : TimeDrivingPort {

    override fun getTime(date: LocalDate): TimeResponse {
        val timetables = timetableQueryTimePort.findTimetableEntitiesByDate(date)
        val times = queryTimePort.findTimeEntitiesByDate(date)
            .map {
                val timetable = timetables.find { timetable -> timetable.timeId == it.id }
                it.toTimeElement(timetable?.periodType?.name ?: PeriodType.DEFAULT.name)
            }

        return TimeResponse(date, times)
    }

    private fun Time.toTimeElement(periodType: String) = TimeElement(
        id = this.id,
        period = this.period,
        beginTime = this.beginTime,
        endTime = this.endTime,
        periodType = periodType,
    )
}
