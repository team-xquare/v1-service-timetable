package com.xquare.v1servicetimetable.adapter.out.persistance.repository

import com.xquare.v1servicetimetable.adapter.out.persistance.domain.TimeEntity
import com.xquare.v1servicetimetable.domain.TableType
import org.springframework.data.repository.CrudRepository

sealed interface TimeRepository : CrudRepository<TimeEntity, Int> {
    fun findAllByType(type: TableType): List<TimeEntity>
}
