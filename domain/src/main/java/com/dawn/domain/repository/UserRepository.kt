package com.dawn.domain.repository

import com.dawn.domain.entity.User

interface UserRepository {
    fun saveUser(user: User)

    fun updateUser(name: String, id: Int)
}