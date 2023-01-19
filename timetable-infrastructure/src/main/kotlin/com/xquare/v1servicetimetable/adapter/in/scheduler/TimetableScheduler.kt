package com.xquare.v1servicetimetable.adapter.`in`.scheduler

import com.xquare.v1servicetimetable.adapter.`in`.cron.TimetableCron
import com.xquare.v1servicetimetable.adapter.out.persistance.domain.TimetableEntity
import com.xquare.v1servicetimetable.adapter.out.persistance.exception.SubjectNotFoundException
import com.xquare.v1servicetimetable.adapter.out.persistance.repository.SubjectRepository
import com.xquare.v1servicetimetable.adapter.out.persistance.repository.TimetableRepository
import com.xquare.v1servicetimetable.domain.TableType
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime

@Component
class TimetableScheduler(
    private val timetableCron: TimetableCron,
    private val subjectRepository: SubjectRepository,
    private val timetableRepository: TimetableRepository
) {
    companion object {
        const val START_TIME = "START_TIME"
        const val END_TIME = "END_TIME"
    }

    //@Scheduled(cron = "* * * * * *", zone = "Asia/Seoul") TODO: cron 날짜 정하기(1학기, 2학기)
    @Transactional
    fun timetableScheduler() {
        val subjectEntityList = subjectRepository.findAll()

        for (i in 1..3) {
            for (j in 1..4) {
                val timetableEntityList: MutableList<TimetableEntity> = mutableListOf()
                timetableCron.cron(grade = i.toString(), `class` = j.toString())
                    .forEach { timetable ->
                        val subjectEntity = subjectEntityList.find { it.name == timetable.subject }
                            ?: throw SubjectNotFoundException

                        val timetableEntity = TimetableEntity(
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
                        timetableEntityList.add(timetableEntity)
                    }

                timetableRepository.saveAll(timetableEntityList)
            }
        }
    }

    private fun getPeriodTime(type: String, period: Int): LocalDateTime {
        val year: Int = LocalDate.now().year

        return if (type == START_TIME) {
            if (period <= 4) {
                LocalDateTime.of(year, 1, 1, period + 7, 40)
            } else {
                LocalDateTime.of(year, 1, 1, period + 8, 30)
            }
        } else {
            if (period <= 4) {
                LocalDateTime.of(year, 1, 1, period + 8, 30)
            } else {
                LocalDateTime.of(year, 1, 1, period + 9, 20)
            }
        }
    }
}
