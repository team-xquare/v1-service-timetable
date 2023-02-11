package com.xquare.v1servicetimetable.timetable.port.out

import com.xquare.v1servicetimetable.time.port.out.TimetableQueryTimePort

interface TimetableDrivenPort : QueryTimetablePort, CommandTimetablePort, TimetableQueryTimePort
