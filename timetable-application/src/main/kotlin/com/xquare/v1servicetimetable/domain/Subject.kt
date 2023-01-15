package com.xquare.v1servicetimetable.domain

import com.xquare.v1servicetimetable.common.annotation.Aggregate
import java.util.UUID

@Aggregate
class Subject(
    val id: UUID,
    val name: String,
    val profile: String
)
