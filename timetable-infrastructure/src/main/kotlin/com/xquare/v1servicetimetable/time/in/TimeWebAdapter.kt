package com.xquare.v1servicetimetable.time.`in`

import com.xquare.v1servicetimetable.time.port.`in`.TimeDrivingPort
import com.xquare.v1servicetimetable.time.port.`in`.dto.response.TimeResponse
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class TimeWebAdapter(
    private val timeDrivingPort: TimeDrivingPort
) {

    @GetMapping("/{date}")
    fun getTime(
        @PathVariable("date")
        @DateTimeFormat(iso = ISO.DATE)
        date: LocalDate
    ): TimeResponse = timeDrivingPort.getTime(date)
}
