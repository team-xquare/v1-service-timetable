package com.xquare.v1servicetimetable.adapter.out.persistance.mapper

import com.xquare.v1servicetimetable.adapter.out.persistance.domain.SubjectEntity
import com.xquare.v1servicetimetable.domain.Subject
import org.springframework.stereotype.Component

@Component
class SubjectMapper {
    fun domainToEntity(subject: Subject): SubjectEntity {
        return SubjectEntity(
            id = subject.id,
            name = subject.name,
            profile = subject.profile
        )
    }

    fun entityToDomain(subjectEntity: SubjectEntity): Subject {
        return Subject(
            id = subjectEntity.id,
            name = subjectEntity.name,
            profile = subjectEntity.profile
        )
    }
}
