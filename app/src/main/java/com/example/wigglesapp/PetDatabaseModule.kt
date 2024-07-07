package com.example.wigglesapp

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PetDatabaseModule{

    @Provides
    @Singleton
    fun provideDatabase(app: Application): PetDatabase{
        return Room.databaseBuilder(app, PetDatabase::class.java, "pet_database").build()
    }

    @Provides
    fun providePetDao(db: PetDatabase): PetDao{
        return db.petDao()
    }

}