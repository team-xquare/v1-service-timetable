package com.xquare.v1servicetimetable.common.error

abstract class BaseException(
    val errorCode: ErrorCode
) : RuntimeException() {
    override fun fillInStackTrace(): Throwable {
        return this
    }
}
