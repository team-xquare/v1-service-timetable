package com.xquare.v1servicetimetable.adapter.out.thirdparty.feign.error

import com.xquare.v1servicetimetable.common.error.BaseException
import com.xquare.v1servicetimetable.common.error.ErrorCode

object FeignForbiddenException : BaseException(ErrorCode.FEIGN_FORBIDDEN)
