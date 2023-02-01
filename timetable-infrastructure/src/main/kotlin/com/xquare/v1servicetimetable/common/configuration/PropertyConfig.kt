package com.xquare.v1servicetimetable.common.configuration

import com.xquare.v1servicetimetable.BASE_PACKAGE
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@ConfigurationPropertiesScan(basePackages = [BASE_PACKAGE])
@Configuration
class PropertyConfig
