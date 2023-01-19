package com.xquare.v1servicetimetable.adapter.`in`.cron.dto

import java.time.LocalDate

data class TimetableElement(
    val date: LocalDate,
    val period: Int,
    val subject: String
)
