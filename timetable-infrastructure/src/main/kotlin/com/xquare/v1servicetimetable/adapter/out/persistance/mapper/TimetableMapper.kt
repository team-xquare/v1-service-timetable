package com.xquare.v1servicetimetable.adapter.out.persistance.mapper

import com.xquare.v1servicetimetable.adapter.out.persistance.domain.SubjectEntity
import com.xquare.v1servicetimetable.adapter.out.persistance.domain.TimeEntity
import com.xquare.v1servicetimetable.adapter.out.persistance.domain.TimetableEntity
import com.xquare.v1servicetimetable.adapter.out.persistance.exception.SubjectNotFoundException
import com.xquare.v1servicetimetable.adapter.out.persistance.exception.TimeNotFoundException
import com.xquare.v1servicetimetable.adapter.out.persistance.repository.SubjectRepository
import com.xquare.v1servicetimetable.adapter.out.persistance.repository.TimeRepository
import com.xquare.v1servicetimetable.domain.Timetable
import org.springframework.stereotype.Component

@Component
class TimetableMapper(
    private val subjectRepository: SubjectRepository,
    private val timeRepository: TimeRepository
) {
    fun domainToEntity(timetable: Timetable): TimetableEntity {
        val subjectEntity: SubjectEntity = subjectRepository.findById(timetable.subjectId)
            .orElseThrow { throw SubjectNotFoundException }
        val timeEntity: TimeEntity = timeRepository.findById(timetable.timeId)
            .orElseThrow { throw TimeNotFoundException }

        return TimetableEntity(
            id = timetable.id,
            weekDay = timetable.weekDay,
            date = timetable.date,
            grade = timetable.grade,
            classNumber = timetable.classNumber,
            type = timetable.type,
            subjectEntity = subjectEntity,
            timeEntity = timeEntity
        )
    }

    fun entityToDomain(timetableEntity: TimetableEntity): Timetable {
        return Timetable(
            id = timetableEntity.id,
            weekDay = timetableEntity.weekDay,
            date = timetableEntity.date,
            grade = timetableEntity.grade,
            classNumber = timetableEntity.classNumber,
            type = timetableEntity.type,
            subjectId = timetableEntity.subjectEntity.id,
            timeId = timetableEntity.timeEntity.id
        )
    }
}
