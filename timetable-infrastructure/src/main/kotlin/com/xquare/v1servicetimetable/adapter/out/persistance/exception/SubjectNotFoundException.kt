package com.xquare.v1servicetimetable.adapter.out.persistance.exception

import com.xquare.v1servicetimetable.common.error.BaseException
import com.xquare.v1servicetimetable.common.error.ErrorCode

object SubjectNotFoundException : BaseException(ErrorCode.SUBJECT_NOT_FOUND)
