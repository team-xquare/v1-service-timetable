package com.xquare.v1servicetimetable.adapter.out.persistance.repository

import com.xquare.v1servicetimetable.adapter.out.persistance.domain.ChangedTimetableEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ChangedTimetableRepository : CrudRepository<ChangedTimetableEntity, UUID>
