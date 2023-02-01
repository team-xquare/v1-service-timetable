package com.xquare.v1servicetimetable.time.out

import com.xquare.v1servicetimetable.common.enums.TableType
import com.xquare.v1servicetimetable.time.domain.Time
import com.xquare.v1servicetimetable.time.port.out.TimeDrivenPort
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class TimeRepositoryAdapter(
    private val timeRepository: TimeRepository,
    private val timeMapper: TimeMapper
) : TimeDrivenPort {

    override fun queryTime(date: LocalDate): List<Time> {
        val defaultTimeEntities = timeRepository.findAllByType(TableType.DEFAULT)
        val timeEntities = timeRepository.findAllByDateAndType(date, TableType.CHANGED)

        val times = defaultTimeEntities.map { defaultTimeEntity ->
            timeMapper.entityToDomain(
                timeEntities?.let { it ->
                    it.find { it.period == defaultTimeEntity.period }
                } ?: defaultTimeEntity
            )
        }
        return times
    }
}
