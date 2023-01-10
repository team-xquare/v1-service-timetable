package com.xquare.v1servicetimetable.common.error

enum class ErrorCode(
    val statusCode: Int,
    val errorMessage: String
) {
    // Feign
    FEIGN_BAD_REQUEST(statusCode = 400, errorMessage = "Feign Bad Request."),
    FEIGN_UNAUTHORIZED(statusCode = 401, errorMessage = "Feign Unauthorized."),
    FEIGN_FORBIDDEN(statusCode = 403, errorMessage = "Feign Forbidden."),
    FEIGN_EXPIRED_TOKEN(statusCode = 419, errorMessage = "Feign Expired Token."),

    // Subject
    SUBJECT_NOT_FOUND(statusCode = 404, errorMessage = "Subject Not Found."),

    // Etc
    INTERNAL_SERVER_ERROR(statusCode = 500, errorMessage = "Internal Server Error.")
}
