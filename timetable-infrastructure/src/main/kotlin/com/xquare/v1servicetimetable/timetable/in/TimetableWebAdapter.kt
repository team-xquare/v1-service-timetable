package com.xquare.v1servicetimetable.timetable.`in`

import com.xquare.v1servicetimetable.timetable.port.`in`.TimetableDrivingPort
import com.xquare.v1servicetimetable.timetable.port.`in`.dto.response.WeekTimetableResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TimetableWebAdapter(
    private val timetableDrivingPort: TimetableDrivingPort
) {

    @GetMapping("/week")
    fun getWeekTimetable(): WeekTimetableResponse {
        return timetableDrivingPort.getWeekTimetable()
    }
}
