package com.xquare.v1servicetimetable.timetable.out

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TimetableRepository : CrudRepository<TimetableEntity, UUID>
