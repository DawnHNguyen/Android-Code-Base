package com.dawnnguyen.common

import com.dawnnguyen.common.dto.LocalDto
import com.dawnnguyen.common.dto.RemoteDto

interface DomainModel {
    fun toLocalDto(): LocalDto
    fun toRemoteDto(): RemoteDto
}