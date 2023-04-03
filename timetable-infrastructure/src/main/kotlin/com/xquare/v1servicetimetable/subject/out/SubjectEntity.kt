package com.xquare.v1servicetimetable.subject.out

import org.hibernate.annotations.GenericGenerator
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Table(name = "tbl_subject")
@Entity
class SubjectEntity(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    val id: UUID = UUID(0, 0),

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    val name: String,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(1000)")
    val profile: String
)
