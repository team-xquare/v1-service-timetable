package com.xquare.v1servicetimetable.timetable.out

import com.linecorp.kotlinjdsl.QueryFactory
import com.linecorp.kotlinjdsl.listQuery
import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.querydsl.from.join
import com.xquare.v1servicetimetable.subject.out.SubjectEntity
import com.xquare.v1servicetimetable.time.out.TimeEntity
import com.xquare.v1servicetimetable.timetable.domain.Timetable
import com.xquare.v1servicetimetable.timetable.port.out.TimetableDrivenPort
import com.xquare.v1servicetimetable.timetable.port.out.vo.DayTimeElementVO
import org.springframework.stereotype.Component
import java.time.LocalDate
import javax.persistence.criteria.JoinType

@Component
class TimetableRepositoryAdapter(
    private val queryFactory: QueryFactory,
    private val timetableMapper: TimetableMapper
) : TimetableDrivenPort {

    override fun findTimetableEntitiesByDate(date: LocalDate): List<Timetable> =
        queryFactory.findAllByDate(date)
            .map(timetableMapper::entityToDomain)

    override fun findTimetableEntitiesByDateBetweenAndGradeAndClassNum(
        start: LocalDate,
        end: LocalDate,
        grade: Int,
        classNum: Int,
    ): Map<LocalDate, List<DayTimeElementVO>> =
        queryFactory.findAllByDateBetweenAndGradeAndClassNum(
            start = start,
            end = end,
            grade = grade,
            classNum = classNum,
        ).map {
            DayTimeElementVO(
                period = it.period,
                beginTime = it.beginTime,
                endTime = it.endTime,
                subjectName = it.subjectName,
                subjectImage = it.subjectImage,
                date = it.date,
            )
        }.groupBy { it.date }

    private fun QueryFactory.findAllByDateBetweenAndGradeAndClassNum(
        start: LocalDate,
        end: LocalDate,
        grade: Int,
        classNum: Int
    ): List<DayTimeElementVO> {
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
            orderBy(listOf(col(TimetableEntity::weekDay).asc(), col(TimetableEntity::period).asc()))
        }
    }

    private fun QueryFactory.findAllByDate(date: LocalDate): List<TimetableEntity> {
        return this.listQuery {
            select(
                listOf(
                    col(TimetableEntity::id),
                    col(TimetableEntity::weekDay),
                    col(TimetableEntity::date),
                    col(TimetableEntity::grade),
                    col(TimetableEntity::classNum),
                    col(TimetableEntity::tableType),
                    col(TimetableEntity::period),
                    col(TimetableEntity::periodType),
                    col(TimetableEntity::subjectEntity),
                    col(TimetableEntity::timeEntity)
                )
            )
            from(entity(TimetableEntity::class))
            where(
                col(TimetableEntity::date).equal(date)
            )
        }
    }
}
