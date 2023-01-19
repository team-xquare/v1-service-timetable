package com.xquare.v1servicetimetable.adapter.`in`.cron.dto

import java.time.LocalDate

data class ScheduleElement(
    val date: LocalDate,
    val name: String
)
