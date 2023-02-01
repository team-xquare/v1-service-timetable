package com.xquare.v1servicetimetable.config.exception

import com.xquare.v1servicetimetable.common.error.BaseException
import com.xquare.v1servicetimetable.common.error.ErrorCode

object ConfigNotFoundException : BaseException(ErrorCode.CONFIG_NOT_FOUND)
