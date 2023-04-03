package com.xquare.v1servicetimetable.timetable.out

import com.xquare.v1servicetimetable.common.enums.PeriodType
import com.xquare.v1servicetimetable.common.enums.TableType
import com.xquare.v1servicetimetable.subject.out.SubjectEntity
import com.xquare.v1servicetimetable.time.out.TimeEntity
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Table(name = "tbl_timetable")
@Entity
class TimetableEntity(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    val id: UUID = UUID(0, 0),

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
    val classNum: Int,

    @Enumerated(EnumType.STRING)
    @field:NotNull
    @Column(columnDefinition = "CHAR(7)")
    val tableType: TableType = TableType.DEFAULT,

    @field:NotNull
    @Column(columnDefinition = "TINYINT(2)")
    val period: Int,

    @Enumerated(EnumType.STRING)
    @field:NotNull
    @Column(columnDefinition = "CHAR(13)")
    val periodType: PeriodType = PeriodType.DEFAULT,

    @field:NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    val subjectEntity: SubjectEntity,

    @field:NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    val timeEntity: TimeEntity
)
