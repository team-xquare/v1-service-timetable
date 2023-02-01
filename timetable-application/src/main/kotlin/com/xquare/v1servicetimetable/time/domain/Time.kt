package com.xquare.v1servicetimetable.time.domain

import com.xquare.v1servicetimetable.common.annotation.Aggregate
import com.xquare.v1servicetimetable.common.enums.TableType
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

@Aggregate
class Time(
    val id: UUID,
    val beginTime: LocalTime,
    val endTime: LocalTime,
    val period: Int,
    val type: TableType,
    val date: LocalDate?
)
