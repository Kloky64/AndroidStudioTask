package com.example.mipt_example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class RestaurantEvent {
    object SwitchScreens: RestaurantEvent()

}

data class RestaurantViewState (
    val nearest: List<Restaurant> = emptyList(),
    val popular: List<Restaurant> = emptyList(),
    val switchScreens: Boolean = false
)

@HiltViewModel
class RestaurantViewModel @Inject constructor(private val resRepository: RestaurantRepository)
    : ViewModel() {

    private val _viewState = MutableStateFlow(RestaurantViewState())
    val viewState: StateFlow<RestaurantViewState> = _viewState

    init {
        fetchRestaurants()
    }

    fun obtainEvent(event: RestaurantEvent) {
        when (event) {
            RestaurantEvent.SwitchScreens -> {_viewState.value =
                _viewState.value.copy(switchScreens = !_viewState.value.switchScreens)}
        }
    }

    private fun fetchRestaurants() {
        viewModelScope.launch {
            val response = resRepository.fetchCatalog()
            _viewState.value = _viewState.value.copy(
                    nearest = response.nearest.map { it.mapToRestaurant() },
                    popular = response.popular.map { it.mapToRestaurant() },
                )
        }
    }
}