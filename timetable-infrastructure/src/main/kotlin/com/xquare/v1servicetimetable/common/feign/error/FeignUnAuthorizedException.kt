package com.xquare.v1servicetimetable.common.feign.error

import com.xquare.v1servicetimetable.common.error.BaseException
import com.xquare.v1servicetimetable.common.error.ErrorCode

object FeignUnAuthorizedException : BaseException(ErrorCode.FEIGN_UNAUTHORIZED)
