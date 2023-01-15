package com.xquare.v1servicetimetable.adapter.out.persistance.domain

import com.xquare.v1servicetimetable.common.entity.BaseUUIDEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Table(name = "tbl_subject")
@Entity
class SubjectEntity(
    override val id: UUID,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    val name: String,

    @field:NotNull
    @Column(columnDefinition = "CHAR(1)")
    val profile: String

) : BaseUUIDEntity(id)
