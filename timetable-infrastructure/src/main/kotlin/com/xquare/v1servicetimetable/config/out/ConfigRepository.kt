package com.xquare.v1servicetimetable.config.out

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ConfigRepository : CrudRepository<ConfigEntity, Int>
