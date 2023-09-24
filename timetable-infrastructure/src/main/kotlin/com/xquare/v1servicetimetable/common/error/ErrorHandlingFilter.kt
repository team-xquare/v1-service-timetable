package com.xquare.v1servicetimetable.common.error

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ErrorHandlingFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: BaseException) {
            errorResponse(e.errorCode, response)
        } catch (e: Exception) {
            when (e.cause) {
                is BaseException -> {
                    errorResponse((e.cause as BaseException).errorCode, response)
                }

                else -> {
                    e.printStackTrace()
                    errorResponse(ErrorCode.INTERNAL_SERVER_ERROR, response)
                }
            }
        }
    }

    private fun errorResponse(errorCode: ErrorCode, response: HttpServletResponse) {
        response.apply {
            status = errorCode.statusCode
            contentType = MediaType.APPLICATION_JSON_VALUE
            writer.write(objectMapper.writeValueAsString(ErrorResponse(errorCode)))
        }
    }
}
