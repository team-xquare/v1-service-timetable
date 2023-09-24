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

    companion object {
        const val GRADE_COUNT = 3
        const val CLASS_NUM_COUNT = 4
    }

    @Transactional
    @Scheduled(cron = "0 59 23 * * SAT", zone = "Asia/Seoul")
    fun timetableScheduler() {
        timetableRepository.deleteAll()

        val subjectEntityMap = subjectRepository.findAll()
            .associateBy { it.name }
            .toMutableMap()

        val timeEntityMap = timeRepository.findAllByType(TableType.DEFAULT)
            .associateBy { it.period }

        for (grade in 1..GRADE_COUNT) {
            for (classNum in 1..CLASS_NUM_COUNT) {
                val timetableEntityList = timetableCron.timetableCron(
                    grade = grade.toString(),
                    classNum = classNum.toString(),
                ).map { timetable ->
                    val subjectEntity = subjectEntityMap[timetable.subject]
                        ?: createNewSubjectEntity(timetable.subject, subjectEntityMap)

                    val timeEntity = timeEntityMap[timetable.period]
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

    private fun createNewSubjectEntity(
        subjectName: String,
        subjectEntityMap: MutableMap<String, SubjectEntity>,
    ): SubjectEntity {
        val newSubjectEntity = SubjectEntity(
            name = subjectName,
            profile = defaultSubjectImage,
        ).also { subjectEntityMap[it.name] = it }
        return subjectRepository.save(newSubjectEntity)
    }
}
