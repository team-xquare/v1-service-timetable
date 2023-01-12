package com.xquare.v1servicetimetable.adapter.out.persistance.mapper

import com.xquare.v1servicetimetable.adapter.out.persistance.domain.DefaultTimetableEntity
import com.xquare.v1servicetimetable.adapter.out.persistance.domain.SubjectEntity
import com.xquare.v1servicetimetable.adapter.out.persistance.exception.SubjectNotFoundException
import com.xquare.v1servicetimetable.adapter.out.persistance.repository.SubjectRepository
import com.xquare.v1servicetimetable.domain.DefaultTimetable
import org.springframework.stereotype.Component

@Component
class DefaultTimetableMapper(
    private val subjectRepository: SubjectRepository
) {
    fun domainToEntity(defaultTimetable: DefaultTimetable): DefaultTimetableEntity {
        val subjectEntity: SubjectEntity = subjectRepository.findById(defaultTimetable.subjectId)
            .orElseThrow { throw SubjectNotFoundException }

        return DefaultTimetableEntity(
            id = defaultTimetable.id,
            period = defaultTimetable.period,
            beginTime = defaultTimetable.beginTime,
            endTime = defaultTimetable.endTime,
            weekDay = defaultTimetable.weekDay,
            grade = defaultTimetable.grade,
            `class` = defaultTimetable.`class`,
            subjectEntity = subjectEntity
        )
    }

    fun entityToDomain(defaultTimetableEntity: DefaultTimetableEntity): DefaultTimetable {
        return DefaultTimetable(
            id = defaultTimetableEntity.id,
            period = defaultTimetableEntity.period,
            beginTime = defaultTimetableEntity.beginTime,
            endTime = defaultTimetableEntity.endTime,
            weekDay = defaultTimetableEntity.weekDay,
            grade = defaultTimetableEntity.grade,
            `class` = defaultTimetableEntity.`class`,
            subjectId = defaultTimetableEntity.subjectEntity.id
        )
    }
}
