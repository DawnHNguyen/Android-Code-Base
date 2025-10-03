package com.dawn.data.di

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.dawn.database.CodebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CodebaseDatabase {
        val driver = AndroidSqliteDriver(CodebaseDatabase.Schema, context, "codebase.db")
        return CodebaseDatabase(
            driver = driver,
        )
    }
}