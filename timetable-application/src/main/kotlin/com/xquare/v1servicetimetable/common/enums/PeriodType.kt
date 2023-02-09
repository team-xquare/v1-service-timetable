package com.xquare.v1servicetimetable.common.enums

enum class PeriodType(
    val koreaName: String
) {
    CLUB("동아리"),
    SELF_STUDY("자습"),
    EXAM("시험"),
    EDUCATION("교육"),
    DEFAULT("정규수업")
}
