package com.xquare.v1servicetimetable.adapter.out.persistance.mapper

import com.xquare.v1servicetimetable.adapter.out.persistance.domain.SubjectEntity
import com.xquare.v1servicetimetable.adapter.out.persistance.domain.TimetableEntity
import com.xquare.v1servicetimetable.adapter.out.persistance.exception.SubjectNotFoundException
import com.xquare.v1servicetimetable.adapter.out.persistance.repository.SubjectRepository
import com.xquare.v1servicetimetable.domain.Timetable
import org.springframework.stereotype.Component

@Component
class TimetableMapper(
    private val subjectRepository: SubjectRepository
) {
    fun domainToEntity(timetable: Timetable): TimetableEntity {
        val subjectEntity: SubjectEntity = subjectRepository.findById(timetable.subjectId)
            .orElseThrow { throw SubjectNotFoundException }

        return TimetableEntity(
            id = timetable.id,
            period = timetable.period,
            beginTime = timetable.beginTime,
            endTime = timetable.endTime,
            weekDay = timetable.weekDay,
            date = timetable.date,
            grade = timetable.grade,
            classNumber = timetable.classNumber,
            type = timetable.type,
            subjectEntity = subjectEntity
        )
    }

    fun entityToDomain(timetableEntity: TimetableEntity): Timetable {
        return Timetable(
            id = timetableEntity.id,
            period = timetableEntity.period,
            beginTime = timetableEntity.beginTime,
            endTime = timetableEntity.endTime,
            weekDay = timetableEntity.weekDay,
            date = timetableEntity.date,
            grade = timetableEntity.grade,
            classNumber = timetableEntity.classNumber,
            type = timetableEntity.type,
            subjectId = timetableEntity.subjectEntity.id
        )
    }
}
