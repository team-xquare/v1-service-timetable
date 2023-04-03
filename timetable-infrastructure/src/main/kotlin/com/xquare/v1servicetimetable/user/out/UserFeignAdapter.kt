package com.xquare.v1servicetimetable.user.out

import com.xquare.v1servicetimetable.common.feign.client.UserClient
import com.xquare.v1servicetimetable.user.User
import com.xquare.v1servicetimetable.user.port.UserDrivenPort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserFeignAdapter(
    private val userClient: UserClient
) : UserDrivenPort {

    override fun getUser(userId: UUID): User {
        return userClient.getUser(userId)
            .let {
                User(
                    id = it.id,
                    accountId = it.accountId,
                    password = it.password,
                    name = it.name,
                    grade = it.grade,
                    classNum = it.classNum,
                    num = it.num,
                    birthDay = it.birthDay,
                    profileFileName = it.profileFileName
                )
            }
    }

    override fun getCurrentUserId(): UUID {
        return UUID.fromString(SecurityContextHolder.getContext().authentication.name)
    }
}
