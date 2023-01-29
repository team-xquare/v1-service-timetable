package com.xquare.v1servicetimetable.adapter.out.persistance.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Table(name = "tbl_config")
@Entity
class ConfigEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @field:NotNull
    @Column(columnDefinition = "TINYINT(1)")
    val classCount: Int
)
