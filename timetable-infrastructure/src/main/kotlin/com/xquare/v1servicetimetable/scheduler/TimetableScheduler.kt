package com.xquare.v1servicetimetable.scheduler

import com.xquare.v1servicetimetable.common.enums.TableType
import com.xquare.v1servicetimetable.cron.TimetableCron
import com.xquare.v1servicetimetable.subject.out.SubjectEntity
import com.xquare.v1servicetimetable.subject.out.SubjectRepository
import com.xquare.v1servicetimetable.time.exception.TimeNotFoundException
import com.xquare.v1servicetimetable.time.out.TimeRepository
import com.xquare.v1servicetimetable.timetable.out.TimetableEntity
import com.xquare.v1servicetimetable.timetable.out.TimetableRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class TimetableScheduler(
    private val timetableCron: TimetableCron,
    private val subjectRepository: SubjectRepository,
    private val timetableRepository: TimetableRepository,
    private val timeRepository: TimeRepository,
    @Value("\${subject.image}")
    private val defaultSubjectImage: String,
) {

    @Transactional
    @Scheduled(cron = "0 59 23 * * SAT", zone = "Asia/Seoul")
    fun timetableScheduler() {
        timetableRepository.deleteAll()

        val subjectEntityList = subjectRepository.findAll()
        val timeEntityList = timeRepository.findAllByType(TableType.DEFAULT)

        for (grade in 1..3) {
            for (classNum in 1..4) {
                val timetableEntityList = timetableCron.timetableCron(
                    grade = grade.toString(),
                    classNum = classNum.toString(),
                ).map { timetable ->
                    val subjectEntity = subjectEntityList.find { it.name == timetable.subject }
                        ?: getOrCreateSubjectEntity(timetable.subject)

                    val timeEntity = timeEntityList.find { it.period == timetable.period }
                        ?: throw TimeNotFoundException

                    TimetableEntity(
                        weekDay = timetable.date.dayOfWeek.value,
                        date = timetable.date,
                        grade = grade,
                        classNum = classNum,
                        period = timetable.period,
                        subjectEntity = subjectEntity,
                        timeEntity = timeEntity,
                    )
                }
                timetableRepository.saveAll(timetableEntityList)
            }
        }
    }

    private fun getOrCreateSubjectEntity(subjectName: String): SubjectEntity {
        return subjectRepository.findByName(subjectName) ?: subjectRepository.save(
            SubjectEntity(
                name = subjectName,
                profile = defaultSubjectImage,
            )
        )
    }
}
