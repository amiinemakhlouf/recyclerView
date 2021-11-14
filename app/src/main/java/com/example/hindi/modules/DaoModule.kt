package com.example.hindi.modules

import com.example.hindi.dataSource.room.NoteDAo
import com.example.hindi.dataSource.room.RoomDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Singleton
    @Provides
    fun provideDao(db: RoomDb):NoteDAo
    {
        return db.getNoteDao()
    }
}
