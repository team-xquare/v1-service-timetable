package com.xquare.v1servicetimetable.adapter.out.persistance.domain

import com.xquare.v1servicetimetable.domain.TableType
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Table(name = "tbl_time")
@Entity
class TimeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @field:NotNull
    @Column(columnDefinition = "TIME")
    val beginTime: LocalTime,

    @field:NotNull
    @Column(columnDefinition = "TIME")
    val endTime: LocalTime,

    @field:NotNull
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0")
    val period: Int,

    @Enumerated(EnumType.STRING)
    @field:NotNull
    @Column(columnDefinition = "CHAR(7)")
    val type: TableType,

    @Column(columnDefinition = "DATE")
    val date: LocalDate,
)
