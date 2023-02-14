package com.xquare.v1servicetimetable.user.port

import com.xquare.v1servicetimetable.user.User
import java.util.*

interface UserPort {

    fun getUser(userId: UUID): User

    fun getCurrentUserId(): UUID
}
