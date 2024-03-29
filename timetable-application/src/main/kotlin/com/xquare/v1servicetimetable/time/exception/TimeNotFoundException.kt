package com.xquare.v1servicetimetable.time.exception

import com.xquare.v1servicetimetable.common.error.BaseException
import com.xquare.v1servicetimetable.common.error.ErrorCode

object TimeNotFoundException : BaseException(ErrorCode.TIME_NOT_FOUND)
