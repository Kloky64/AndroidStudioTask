package com.example.mipt_example.data;

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RestaurantRepository @Inject constructor(private val httpClient: HttpClient,
   private val restaurantDao: RestaurantDao) {
    suspend fun fetchCatalog(): Flow<CatalogResponse> {
        return flow {
            val cache = restaurantDao.selectAll()
            if (cache.isNotEmpty()) {
                emit(
                    CatalogResponse(
                        nearest = cache.map{it.mapToRemoteRestaurant()},
                        popular = emptyList(),
                        commercial = Commercial("", "")
                    )
                )
            }

            try {
                val response = httpClient.request("http://195.2.84.138:8081/catalog") {
                    method = HttpMethod.Get
                }.body<CatalogResponse>()

                restaurantDao.insert(*response.nearest.map {
                    it.mapToDBRestaurant()
                }.toTypedArray())

                emit(response)
            } catch (e: Exception) {
                print(e.stackTrace)
            }

        }
    }
}
