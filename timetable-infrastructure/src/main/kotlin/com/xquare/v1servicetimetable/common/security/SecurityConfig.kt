package com.xquare.v1servicetimetable.common.security

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
class SecurityConfig {

    @Bean
    @Throws(Exception::class)
    protected fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .formLogin().disable()
            .csrf().disable()
            .cors().and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http
            .authorizeRequests()
            .anyRequest().permitAll()

        return http.build()
    }
}
