package com.dawn.data.repository

import com.dawn.domain.entity.User
import com.dawn.domain.repository.UserRepository
import com.dawn.data.local.UserLocalDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val localDataSource: UserLocalDataSource) : UserRepository {

    override fun saveUser(user: User) = localDataSource.addUser(user)

    override fun updateUser(
        name: String,
        id: Int,
    ) = localDataSource.updateUser(
        name = name,
        id = id.toLong()
    )
}