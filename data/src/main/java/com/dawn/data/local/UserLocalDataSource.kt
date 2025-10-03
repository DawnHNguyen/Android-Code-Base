package com.dawn.data.local

import com.dawn.database.CodebaseDatabase
import com.dawn.domain.entity.User
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(database: CodebaseDatabase) {
    private val dbQueries = database.codebaseDatabaseQueries

    fun addUser(user: User) =
        dbQueries.addUser(
            name = user.name
        )

    fun updateUser(name: String, id: Long) =
        dbQueries.updateUser(
            name = name,
            id = id
        )
}