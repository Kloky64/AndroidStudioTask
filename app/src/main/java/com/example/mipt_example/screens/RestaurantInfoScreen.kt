package com.example.mipt_example.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.mipt_example.R
import com.example.mipt_example.data.NetRestaurant


data class Restaurant (
    val name: String,
    val deliveryTime: String,
    val image: String
)

fun NetRestaurant.mapToRestaurant(): Restaurant {
    return Restaurant(name = name, deliveryTime = deliveryTime, image = image)
}

@Preview(showBackground = true)
@Composable
fun ResScreenView() {
    val resViewModel: RestaurantViewModel = viewModel()
    val viewState = resViewModel.viewState.collectAsState()
    Box(modifier = Modifier.background(color = Color.Cyan), contentAlignment = Alignment.TopEnd) {
        Switch(checked = viewState.value.switchScreens, onCheckedChange =
        {resViewModel.obtainEvent(RestaurantEvent.SwitchScreens)})
    }
    PrintRestaurantInfo(restaurants =
    if (viewState.value.switchScreens) viewState.value.nearest else viewState.value.popular)
}

@Composable
fun PrintRestaurantInfo(restaurants: List<Restaurant>) {
    LazyColumn(modifier = Modifier
        .padding(top = 40.dp)
        .background(color = Color.White)) {
        items(restaurants) { restaurant ->
            Text(stringResource(R.string.Restaurant_desc) + restaurant.name,
                textAlign = TextAlign.Center)
            Text(text = stringResource(R.string.Delivery_loss) + restaurant.deliveryTime,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 10.dp))
            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(restaurant.image)
                .decoderFactory(SvgDecoder.Factory())
                .build()
                , contentDescription = null,
                modifier = Modifier.padding(top = 30.dp))

        }
    }
}



