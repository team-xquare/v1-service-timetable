package com.xquare.v1servicetimetable.common.feign.client.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import java.util.*

data class UserResponse(
    val id: UUID,
    val name: String,
    @JsonProperty("birth_day")
    val birthDay: LocalDate,
    val grade: Int,
    @JsonProperty("class_num")
    val classNum: Int,
    @JsonProperty("profile_file_name")
    val profileFileName: String?,
    val password: String,
    @JsonProperty("account_id")
    val accountId: String,
    val num: Int
)
