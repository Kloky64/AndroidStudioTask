package com.example.mipt_example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mipt_example.data.RestaurantDBEntity

@Database(entities = [RestaurantDBEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}