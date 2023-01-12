package com.xquare.v1servicetimetable.adapter.out.persistance.mapper

import com.xquare.v1servicetimetable.adapter.out.persistance.domain.ChangedTimetableEntity
import com.xquare.v1servicetimetable.adapter.out.persistance.domain.SubjectEntity
import com.xquare.v1servicetimetable.adapter.out.persistance.exception.SubjectNotFoundException
import com.xquare.v1servicetimetable.adapter.out.persistance.repository.SubjectRepository
import com.xquare.v1servicetimetable.domain.ChangedTimetable
import org.springframework.stereotype.Component

@Component
class ChangedTimetableMapper(
    private val subjectRepository: SubjectRepository
) {
    fun domainToEntity(changedTimetable: ChangedTimetable): ChangedTimetableEntity {
        val subjectEntity: SubjectEntity = subjectRepository.findById(changedTimetable.subjectId)
            .orElseThrow { throw SubjectNotFoundException }

        return ChangedTimetableEntity(
            id = changedTimetable.id,
            period = changedTimetable.period,
            beginTime = changedTimetable.beginTime,
            endTime = changedTimetable.endTime,
            weekDay = changedTimetable.weekDay,
            date = changedTimetable.date,
            grade = changedTimetable.grade,
            `class` = changedTimetable.`class`,
            subjectEntity = subjectEntity
        )
    }

    fun entityToDomain(changedTimetableEntity: ChangedTimetableEntity): ChangedTimetable {
        return ChangedTimetable(
            id = changedTimetableEntity.id,
            period = changedTimetableEntity.period,
            beginTime = changedTimetableEntity.beginTime,
            endTime = changedTimetableEntity.endTime,
            weekDay = changedTimetableEntity.weekDay,
            date = changedTimetableEntity.date,
            grade = changedTimetableEntity.grade,
            `class` = changedTimetableEntity.`class`,
            subjectId = changedTimetableEntity.subjectEntity.id
        )
    }
}
