package com.example.mipt_example.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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

@Composable
fun RestaurantInfoScreen(resViewModel: RestaurantViewModel, navController: NavController) {
    val viewState = resViewModel.viewState.collectAsState()
    val context = LocalContext.current

    LazyColumn(modifier = Modifier
        .background(color = Color.White).fillMaxWidth()) {

        item {
            Box(modifier = Modifier.background(color = Color.Cyan).fillMaxWidth(),
                contentAlignment = Alignment.TopEnd) {
                Switch(checked = viewState.value.switchScreens, onCheckedChange =
                {resViewModel.obtainEvent(RestaurantEvent.SwitchScreens)})
            }
        }
        item {
            Spacer(modifier = Modifier.height(40.dp))
        }

        val restaurants =
            if (viewState.value.switchScreens) viewState.value.nearest else
                viewState.value.popular
        items(restaurants) { restaurant ->
            Text(stringResource(R.string.Restaurant_desc) + restaurant.name,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = stringResource(R.string.Delivery_loss) + restaurant.deliveryTime,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(30.dp))
            AsyncImage(model = ImageRequest.Builder(context = context)
                .data(restaurant.image)
                .decoderFactory(SvgDecoder.Factory())
                .build()
                , contentDescription = null,
                modifier = Modifier.clickable
                { navController.navigate("detail/${restaurant.name}") })

        }
    }

}




