package com.example.mipt_example.data

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update

@Entity(tableName = "restaurants")
data class RestaurantDBEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "logo") val logo: String,
    @ColumnInfo(name = "deliveryTime") val time: String
)

fun RestaurantDBEntity.mapToRemoteRestaurant(): NetRestaurant =
    NetRestaurant(id = id, name = name, deliveryTime = time, image = logo)

fun NetRestaurant.mapToDBRestaurant(): RestaurantDBEntity =
    RestaurantDBEntity(id = id, name = name, logo = image, time = deliveryTime)

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurants")
    suspend fun selectAll(): List<RestaurantDBEntity>

    @Insert
    suspend fun insert(vararg restaurants: RestaurantDBEntity)

    @Update
    suspend fun update(restaurant: RestaurantDBEntity)
}