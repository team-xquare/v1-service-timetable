package com.xquare.v1servicetimetable.adapter.out.persistance.domain

import com.xquare.v1servicetimetable.common.entity.BaseUUIDEntity
import com.xquare.v1servicetimetable.domain.TableType
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Table(name = "tbl_timetable")
@Entity
class TimetableEntity(
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

    @Column(columnDefinition = "DATE")
    val date: LocalDate,

    @field:NotNull
    @Column(columnDefinition = "TINYINT(1)")
    val grade: Int,

    @field:NotNull
    @Column(columnDefinition = "TINYINT(1)")
    val classNumber: Int,

    @Enumerated(EnumType.STRING)
    @field:NotNull
    @Column(columnDefinition = "CHAR(7)")
    val type: TableType,

    @field:NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    val subjectEntity: SubjectEntity

) : BaseUUIDEntity(id)
