package com.xquare.v1servicetimetable.subject.out

import com.xquare.v1servicetimetable.subject.out.SubjectEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SubjectRepository : CrudRepository<SubjectEntity, UUID> {
    fun findByName(name: String): SubjectEntity?

    override fun findAll(): List<SubjectEntity>
}
