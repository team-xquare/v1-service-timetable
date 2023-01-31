package com.xquare.v1servicetimetable.time.out

import com.xquare.v1servicetimetable.common.enums.TableType
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Table(name = "tbl_time")
@Entity
class TimeEntity(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    val id: UUID = UUID(0, 0),

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
    val date: LocalDate?
)
