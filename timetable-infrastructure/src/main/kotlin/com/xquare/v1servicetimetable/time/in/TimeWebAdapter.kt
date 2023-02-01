package com.xquare.v1servicetimetable.time.`in`

import com.xquare.v1servicetimetable.time.port.`in`.TimeDrivingPort
import com.xquare.v1servicetimetable.time.port.`in`.dto.response.TimeResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class TimeWebAdapter(
    private val timeDrivingPort: TimeDrivingPort
) {

    @GetMapping("/{date}")
    fun getTime(@PathVariable("date") date: String): TimeResponse {
        return timeDrivingPort.getTime(LocalDate.parse(date))
    }
}
