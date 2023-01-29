package com.xquare.v1servicetimetable.adapter.out.persistance.repository

import com.xquare.v1servicetimetable.adapter.out.persistance.domain.ConfigEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ConfigRepository : CrudRepository<ConfigEntity, Int>
