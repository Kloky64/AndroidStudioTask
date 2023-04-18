package com.example.mipt_example.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.mipt_example.data.AppDataBase
import com.example.mipt_example.data.RestaurantDao

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    fun providesDataBase(@ApplicationContext context: Context): AppDataBase = Room.databaseBuilder(
        context,
        AppDataBase::class.java, "food_delivery"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun providesRestaurantDao(appDataBase: AppDataBase): RestaurantDao =
        appDataBase.restaurantDao()

}


