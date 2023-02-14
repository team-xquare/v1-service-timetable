package com.xquare.v1servicetimetable.timetable.exception

import com.xquare.v1servicetimetable.common.error.BaseException
import com.xquare.v1servicetimetable.common.error.ErrorCode

object TimetableNotFoundException : BaseException(ErrorCode.TIMETABLE_NOT_FOUND)
