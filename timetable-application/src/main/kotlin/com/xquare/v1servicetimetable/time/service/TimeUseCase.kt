package com.xquare.v1servicetimetable.time.service

import com.xquare.v1servicetimetable.common.annotation.UseCase
import com.xquare.v1servicetimetable.time.port.`in`.TimeDrivingPort
import com.xquare.v1servicetimetable.time.port.`in`.dto.response.TimeResponse
import com.xquare.v1servicetimetable.time.port.`in`.dto.response.TimeResponse.TimeElement
import com.xquare.v1servicetimetable.time.port.out.QueryTimePort
import java.time.LocalDate

@UseCase
class TimeUseCase(
    private val queryTimePort: QueryTimePort
) : TimeDrivingPort {

    override fun getTime(date: LocalDate): TimeResponse {
        val times = queryTimePort.queryTime(date)
            .map {
                TimeElement(
                    id = it.id,
                    period = it.period,
                    beginTime = it.beginTime,
                    endTime = it.endTime
                )
            }

        return TimeResponse(date, times)
    }
}
