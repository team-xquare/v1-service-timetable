package com.xquare.v1servicetimetable.adapter.`in`.scheduler

import com.xquare.v1servicetimetable.adapter.`in`.cron.TimetableCron
import com.xquare.v1servicetimetable.adapter.out.persistance.domain.TimetableEntity
import com.xquare.v1servicetimetable.adapter.out.persistance.exception.ConfigNotFoundException
import com.xquare.v1servicetimetable.adapter.out.persistance.exception.SubjectNotFoundException
import com.xquare.v1servicetimetable.adapter.out.persistance.repository.ConfigRepository
import com.xquare.v1servicetimetable.adapter.out.persistance.repository.SubjectRepository
import com.xquare.v1servicetimetable.adapter.out.persistance.repository.TimetableRepository
import com.xquare.v1servicetimetable.domain.TableType
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalTime

@Component
class TimetableScheduler(
    private val configRepository: ConfigRepository,
    private val timetableCron: TimetableCron,
    private val subjectRepository: SubjectRepository,
    private val timetableRepository: TimetableRepository
) {
    companion object {
        const val START_TIME = "START_TIME"
        const val END_TIME = "END_TIME"
    }

    @Transactional
    @Scheduled(cron = "0 0 23 * * 6", zone = "Asia/Seoul")
    fun timetableScheduler() {
        val config = configRepository.findById(1)
            .orElseThrow { ConfigNotFoundException }
        timetableRepository.deleteAll()

        val subjectEntityList = subjectRepository.findAll()
        for (i in 1..3) {
            for (j in 1..config.classCount) {
                val timetableEntityList: List<TimetableEntity> =
                    timetableCron.timetableCron(grade = i.toString(), `class` = j.toString())
                        .map { timetable ->
                            val subjectEntity = subjectEntityList.find { it.name == timetable.subject }
                                ?: throw SubjectNotFoundException

                            TimetableEntity(
                                period = timetable.period,
                                beginTime = getPeriodTime(START_TIME, timetable.period),
                                endTime = getPeriodTime(END_TIME, timetable.period),
                                weekDay = timetable.date.dayOfWeek.value,
                                date = timetable.date,
                                grade = i,
                                classNumber = j,
                                type = TableType.DEFAULT,
                                subjectEntity = subjectEntity
                            )
                        }
                timetableRepository.saveAll(timetableEntityList)
            }
        }
    }

    private fun getPeriodTime(type: String, period: Int): LocalTime =
        if (type == START_TIME) {
            if (period <= 4) {
                LocalTime.of(period + 7, 40)
            } else {
                LocalTime.of(period + 8, 30)
            }
        } else {
            if (period <= 4) {
                LocalTime.of(period + 8, 30)
            } else {
                LocalTime.of(period + 9, 20)
            }
        }
}
