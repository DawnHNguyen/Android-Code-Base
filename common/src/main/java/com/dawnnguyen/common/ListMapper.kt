package com.dawnnguyen.common

import com.dawnnguyen.common.dto.LocalDto

interface Mapper<I, O> {
    fun map(input: I): O
}

interface ListMapper<I, O> : Mapper<List<I>, List<O>>

class LocalDtoListToDomainModelListMapper :
    ListMapper<LocalDto, DomainModel> {
    override fun map(input: List<LocalDto>): List<DomainModel> =
        input.map { localDto ->
            localDto.mapToDomainModel()
        }
}