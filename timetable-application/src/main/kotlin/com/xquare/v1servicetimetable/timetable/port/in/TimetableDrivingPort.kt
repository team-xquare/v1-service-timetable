package com.xquare.v1servicetimetable.timetable.port.`in`

import com.xquare.v1servicetimetable.timetable.port.`in`.dto.response.WeekTimetableResponse

interface TimetableDrivingPort {

    fun getWeekTimetable(): WeekTimetableResponse
}
