package com.xquare.v1servicetimetable.timetable.out

import com.xquare.v1servicetimetable.timetable.domain.Timetable
import com.xquare.v1servicetimetable.timetable.port.out.TimetableDrivenPort
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class TimetableRepositoryAdapter(
    private val timetableRepository: TimetableRepository,
    private val timetableMapper: TimetableMapper
) : TimetableDrivenPort {

    override fun findTimetableEntitiesByDate(date: LocalDate): List<Timetable> {
        return timetableRepository.findAllByDate(date)
            .map { timetableMapper.entityToDomain(it) }
    }
}