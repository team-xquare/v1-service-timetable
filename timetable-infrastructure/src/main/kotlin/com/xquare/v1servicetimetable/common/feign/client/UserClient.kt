package com.xquare.v1servicetimetable.common.feign.client

import com.xquare.v1servicetimetable.common.feign.client.dto.response.UserResponse
import com.xquare.v1servicetimetable.user.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID

@FeignClient(name = "UserClient", url = "\${service.scheme}://\${service.user.host}")
interface UserClient {

    @GetMapping("/id/{userId}")
    fun getUser(@PathVariable userId: UUID): UserResponse
}
