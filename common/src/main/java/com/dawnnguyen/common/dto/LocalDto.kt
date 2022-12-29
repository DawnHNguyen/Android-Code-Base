package com.dawnnguyen.common.dto

import com.dawnnguyen.common.DomainModel

interface LocalDto {
    fun mapToDomainModel(): DomainModel
    fun mapToRemoteDto(): RemoteDto
}