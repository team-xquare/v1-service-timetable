package com.xquare.v1servicetimetable

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

internal const val BASE_PACKAGE = "com.xquare.v1servicetimetable"

@SpringBootApplication
class V1ServiceTimetableApplication

fun main(args: Array<String>) {
    runApplication<V1ServiceTimetableApplication>(*args)
}
