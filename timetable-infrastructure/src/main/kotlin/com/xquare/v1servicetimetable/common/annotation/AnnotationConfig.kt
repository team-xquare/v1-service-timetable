package com.xquare.v1servicetimetable.common.annotation

import com.xquare.v1servicetimetable.BASE_PACKAGE
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType


@Configuration
@ComponentScan(
    basePackages = [BASE_PACKAGE],
    includeFilters = [
        ComponentScan.Filter(
            type = FilterType.ANNOTATION,
            value = [UseCase::class]
        )
    ]
)
class AnnotationConfig
