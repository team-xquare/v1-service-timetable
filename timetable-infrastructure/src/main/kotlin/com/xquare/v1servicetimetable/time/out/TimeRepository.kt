package com.xquare.v1servicetimetable.time.out

import com.xquare.v1servicetimetable.common.enums.TableType
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate
import java.util.*

sealed interface TimeRepository : CrudRepository<TimeEntity, UUID> {

    fun findAllByType(type: TableType): List<TimeEntity>
}
