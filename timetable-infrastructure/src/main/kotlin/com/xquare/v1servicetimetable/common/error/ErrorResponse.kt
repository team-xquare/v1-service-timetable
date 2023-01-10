package com.xquare.v1servicetimetable.common.error

class ErrorResponse(
    errorCode: ErrorCode
) {
    val statusCode: Int = errorCode.statusCode
    val message: String = errorCode.errorMessage
}
