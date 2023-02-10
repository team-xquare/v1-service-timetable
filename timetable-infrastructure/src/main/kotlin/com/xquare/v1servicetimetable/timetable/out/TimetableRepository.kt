package com.xquare.v1servicetimetable.timetable.out

import com.xquare.v1servicetimetable.timetable.out.TimetableEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface TimetableRepository : CrudRepository<TimetableEntity, UUID> {

    fun findAllByDate(date: LocalDate): List<TimetableEntity>
}
