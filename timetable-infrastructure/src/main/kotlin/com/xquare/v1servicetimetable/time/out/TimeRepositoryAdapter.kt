package com.xquare.v1servicetimetable.time.out

import com.linecorp.kotlinjdsl.QueryFactory
import com.linecorp.kotlinjdsl.listQuery
import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.xquare.v1servicetimetable.common.enums.TableType
import com.xquare.v1servicetimetable.time.domain.Time
import com.xquare.v1servicetimetable.time.port.out.TimeDrivenPort
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class TimeRepositoryAdapter(
    private val timeMapper: TimeMapper,
    private val queryFactory: QueryFactory,
) : TimeDrivenPort {

    override fun findTimeEntitiesByDate(date: LocalDate): List<Time> {
        val defaultTimeEntities = queryFactory.findAllByType(TableType.DEFAULT)
        val timeEntities = queryFactory.findAllByDateAndType(date, TableType.CHANGED)

        val times = defaultTimeEntities.map { defaultTimeEntity ->
            timeMapper.entityToDomain(
                timeEntities.let { it ->
                    it.find { it.period == defaultTimeEntity.period }
                } ?: defaultTimeEntity
            )
        }
        return times
    }

    private fun QueryFactory.findAllByType(type: TableType): List<TimeEntity> {
        return this.listQuery {
            select(
                listOf(
                    col(TimeEntity::id),
                    col(TimeEntity::beginTime),
                    col(TimeEntity::endTime),
                    col(TimeEntity::period),
                    col(TimeEntity::type),
                    col(TimeEntity::date),
                )
            )
            from(entity(TimeEntity::class))
            where(
                col(TimeEntity::type).equal(type)
            )
        }
    }

    private fun QueryFactory.findAllByDateAndType(date: LocalDate, type: TableType): List<TimeEntity> {
        return this.listQuery {
            select(
                listOf(
                    col(TimeEntity::id),
                    col(TimeEntity::beginTime),
                    col(TimeEntity::endTime),
                    col(TimeEntity::period),
                    col(TimeEntity::type),
                    col(TimeEntity::date),
                )
            )
            from(entity(TimeEntity::class))
            where(
                col(TimeEntity::date).equal(date)
                    .and(col(TimeEntity::type).equal(type))
            )
        }
    }
}
