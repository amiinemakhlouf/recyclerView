package com.example.hindi.modules

import android.content.Context
import androidx.room.Room
import com.example.hindi.dataSource.room.RoomDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context):RoomDb  {

        return Room.databaseBuilder(
            context,
            RoomDb::class.java,
            RoomDb.databaseName
        ).fallbackToDestructiveMigration().build()
    }}
