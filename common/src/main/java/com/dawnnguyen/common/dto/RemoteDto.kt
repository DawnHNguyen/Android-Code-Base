package com.dawnnguyen.common.dto

import com.dawnnguyen.common.DomainModel

interface RemoteDto {
    fun mapToDomainModel(): DomainModel
    fun mapToLocalDto(): LocalDto
}