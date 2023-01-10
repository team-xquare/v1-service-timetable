package com.xquare.v1servicetimetable.common.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.xquare.v1servicetimetable.common.error.ErrorHandlingFilter
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig(
    private val objectMapper: ObjectMapper
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(AuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(ErrorHandlingFilter(objectMapper), AuthenticationFilter::class.java)
    }
}
