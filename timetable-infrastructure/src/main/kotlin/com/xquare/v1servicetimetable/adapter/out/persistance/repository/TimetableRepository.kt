package com.xquare.v1servicetimetable.adapter.out.persistance.repository

import com.xquare.v1servicetimetable.adapter.out.persistance.domain.TimetableEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TimetableRepository : CrudRepository<TimetableEntity, UUID>
