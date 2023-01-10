package com.xquare.v1servicetimetable.common.error

class ErrorResponse(
    errorCode: ErrorCode
) {
    private val statusCode: Int = errorCode.statusCode
    private val errorMessage: String = errorCode.errorMessage

    override fun toString(): String {
        return "{\n" +
                "\t\"statusCode\": " + statusCode +
                ",\n\t\"message\": \"" + errorMessage + '\"' +
                "\n}"
    }
}
