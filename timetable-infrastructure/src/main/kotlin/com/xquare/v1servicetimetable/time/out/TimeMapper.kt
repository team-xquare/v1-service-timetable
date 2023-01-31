package com.xquare.v1servicetimetable.time.out

import com.xquare.v1servicetimetable.time.domain.Time
import org.springframework.stereotype.Component

@Component
class TimeMapper {

    fun domainToEntity(time: Time): TimeEntity {
        return TimeEntity(
            id = time.id,
            beginTime = time.beginTime,
            endTime =  time.endTime,
            period = time.period,
            type = time.type,
            date = time.date
        )
    }

    fun entityToDomain(timeEntity: TimeEntity): Time {
        return Time(
            id = timeEntity.id,
            beginTime = timeEntity.beginTime,
            endTime = timeEntity.endTime,
            period = timeEntity.period,
            type = timeEntity.type,
            date = timeEntity.date
        )
    }
}
