package com.xquare.v1servicetimetable.subject.out

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface SubjectRepository : CrudRepository<SubjectEntity, UUID>
