package com.ashutosh.bingo.DI

import android.content.Context
import androidx.room.Room
import com.ashutosh.bingo.Repository.TaskRepository
import com.ashutosh.bingo.data.MIGRATION_1_2
import com.ashutosh.bingo.data.TaskDao
import com.ashutosh.bingo.data.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesLocalDatabase(@ApplicationContext context: Context): TaskDatabase {
        return Room.databaseBuilder(context, TaskDatabase::class.java, "local_db")
            .fallbackToDestructiveMigration()
            .addMigrations(MIGRATION_1_2)
            .build()
    }


    @Provides
    @Singleton
    fun providesTaskDao(db: TaskDatabase): TaskDao {
        return db.taskDao()
    }

    @Provides
    @Singleton
    fun providesTaskRepository(dao: TaskDao): TaskRepository {
        return TaskRepository(dao)
    }
}