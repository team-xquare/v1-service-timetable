package com.xquare.v1servicetimetable.timetable.domain

import com.xquare.v1servicetimetable.common.annotation.Aggregate
import com.xquare.v1servicetimetable.common.enums.PeriodType
import com.xquare.v1servicetimetable.common.enums.TableType
import java.time.LocalDate
import java.util.*

@Aggregate
class Timetable(
    val id: UUID,
    val weekDay: Int,
    val date: LocalDate,
    val grade: Int,
    val classNum: Int,
    val tableType: TableType,
    val period: Int,
    val periodType: PeriodType,
    val subjectId: UUID,
    val timeId: UUID
)
