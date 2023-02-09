package com.xquare.v1servicetimetable.timetable.out

import com.xquare.v1servicetimetable.subject.out.SubjectEntity
import com.xquare.v1servicetimetable.time.out.TimeEntity
import com.xquare.v1servicetimetable.subject.exception.SubjectNotFoundException
import com.xquare.v1servicetimetable.time.exception.TimeNotFoundException
import com.xquare.v1servicetimetable.subject.out.SubjectRepository
import com.xquare.v1servicetimetable.time.out.TimeRepository
import com.xquare.v1servicetimetable.timetable.domain.Timetable
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
            classNum = timetable.classNum,
            tableType = timetable.tableType,
            period = timetable.period,
            periodType = timetable.periodType,
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
            classNum = timetableEntity.classNum,
            tableType = timetableEntity.tableType,
            period = timetableEntity.period,
            periodType = timetableEntity.periodType,
            subjectId = timetableEntity.subjectEntity.id,
            timeId = timetableEntity.timeEntity.id
        )
    }
}
