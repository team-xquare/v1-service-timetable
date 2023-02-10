package com.xquare.v1servicetimetable.scheduler

import com.xquare.v1servicetimetable.cron.TimetableCron
import com.xquare.v1servicetimetable.timetable.out.TimetableEntity
import com.xquare.v1servicetimetable.config.exception.ConfigNotFoundException
import com.xquare.v1servicetimetable.subject.exception.SubjectNotFoundException
import com.xquare.v1servicetimetable.time.exception.TimeNotFoundException
import com.xquare.v1servicetimetable.config.out.ConfigRepository
import com.xquare.v1servicetimetable.subject.out.SubjectRepository
import com.xquare.v1servicetimetable.time.out.TimeRepository
import com.xquare.v1servicetimetable.timetable.out.TimetableRepository
import com.xquare.v1servicetimetable.common.enums.TableType
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class TimetableScheduler(
    private val configRepository: ConfigRepository,
    private val timetableCron: TimetableCron,
    private val subjectRepository: SubjectRepository,
    private val timetableRepository: TimetableRepository,
    private val timeRepository: TimeRepository
) {

    @Transactional
    @Scheduled(cron = "0 0 23 * * 6", zone = "Asia/Seoul")
    fun timetableScheduler() {
        val config = configRepository.findById(1)
            .orElseThrow { ConfigNotFoundException }
        timetableRepository.deleteAll()

        val subjectEntityList = subjectRepository.findAll()
        val timeEntityList = timeRepository.findAllByType(TableType.DEFAULT)
        for (i in 1..3) {
            for (j in 1..config.classCount) {
                val timetableEntityList: List<TimetableEntity> =
                    timetableCron.timetableCron(grade = i.toString(), classNum = j.toString())
                        .map { timetable ->
                            val subjectEntity = subjectEntityList.find { it.name == timetable.subject }
                                ?: throw SubjectNotFoundException
                            val timeEntity = timeEntityList.find { it.period == timetable.period }
                                ?: throw TimeNotFoundException

                            TimetableEntity(
                                weekDay = timetable.date.dayOfWeek.value,
                                date = timetable.date,
                                grade = i,
                                classNum = j,
                                period = timetable.period,
                                subjectEntity = subjectEntity,
                                timeEntity = timeEntity
                            )
                        }
                timetableRepository.saveAll(timetableEntityList)
            }
        }
    }
}
