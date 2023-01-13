package com.xquare.v1servicetimetable.adapter.out.persistance.domain

import com.xquare.v1servicetimetable.common.entity.BaseUUIDEntity
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Table(name = "tbl_changed_timetable")
@Entity
class ChangedTimetableEntity(
    override val id: UUID,

    @field:NotNull
    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    val period: Int,

    @field:NotNull
    @Column(columnDefinition = "TIME")
    val beginTime: LocalDateTime,

    @field:NotNull
    @Column(columnDefinition = "TIME")
    val endTime: LocalDateTime,

    @field:NotNull
    @Column(columnDefinition = "TINYINT(2)")
    val weekDay: Int,

    @field:NotNull
    @Column(columnDefinition = "DATE")
    val date: LocalDate = LocalDate.now(),

    @field:NotNull
    @Column(columnDefinition = "TINYINT(1)")
    val grade: Int,

    @field:NotNull
    @Column(columnDefinition = "TINYINT(1)", nullable = false)
    val `class`: Int,

    @field:NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    val subjectEntity: SubjectEntity

) : BaseUUIDEntity(id)