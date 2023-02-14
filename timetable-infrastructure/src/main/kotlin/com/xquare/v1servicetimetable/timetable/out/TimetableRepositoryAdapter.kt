package com.xquare.v1servicetimetable.timetable.out

import com.linecorp.kotlinjdsl.QueryFactory
import com.linecorp.kotlinjdsl.listQuery
import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.querydsl.from.join
import com.xquare.v1servicetimetable.subject.out.SubjectEntity
import com.xquare.v1servicetimetable.time.out.TimeEntity
import com.xquare.v1servicetimetable.timetable.domain.Timetable
import com.xquare.v1servicetimetable.timetable.port.`in`.dto.response.DayTimeElement
import com.xquare.v1servicetimetable.timetable.port.out.TimetableDrivenPort
import org.springframework.stereotype.Component
import java.time.LocalDate
import javax.persistence.criteria.JoinType

@Component
class TimetableRepositoryAdapter(
    private val queryFactory: QueryFactory
) : TimetableDrivenPort {

    override fun findTimetableEntitiesByDateAndGradeAndClassNum(
        start: LocalDate,
        end: LocalDate,
        grade: Int,
        classNum: Int
    ): Map<LocalDate, List<DayTimeElement>> {
        return queryFactory.findAllByDateBetweenAndGradeAndClassNum(start, end, grade, classNum)
            .map {
                DayTimeElement(
                    period = it.period,
                    beginTime = it.beginTime,
                    endTime = it.endTime,
                    subjectName = it.subjectName,
                    subjectImage = it.subjectImage,
                    date = it.date
                )
            }.groupBy { it.date }
    }

    private fun QueryFactory.findAllByDateBetweenAndGradeAndClassNum(
        start: LocalDate,
        end: LocalDate,
        grade: Int,
        classNum: Int
    ): List<DayTimeElement> {
        return this.listQuery {
            select(
                listOf(
                    col(TimetableEntity::period),
                    col(TimeEntity::beginTime),
                    col(TimeEntity::endTime),
                    col(SubjectEntity::name),
                    col(SubjectEntity::profile),
                    col(TimetableEntity::date)
                )
            )
            from(entity(TimetableEntity::class))
            join(TimetableEntity::timeEntity, JoinType.LEFT)
            join(TimetableEntity::subjectEntity, JoinType.LEFT)
            where(
                col(TimetableEntity::date).between(start, end)
                    .and(col(TimetableEntity::grade).equal(grade))
                    .and(col(TimetableEntity::classNum).equal(classNum))
            )
            orderBy(col(TimetableEntity::weekDay).asc())
            orderBy(col(TimetableEntity::period).asc())
        }
    }
}
