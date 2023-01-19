package com.xquare.v1servicetimetable.adapter.`in`.cron.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "neis")
@ConstructorBinding
data class NeisProperties(
    var key: String,
    var type: String,
    var size: Int,
    var region: String,
    var school: String
)
