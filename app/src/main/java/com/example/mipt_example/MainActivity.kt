package com.example.mipt_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mipt_example.ui.theme.Mipt_exampleTheme
import dagger.hilt.android.AndroidEntryPoint
import com.example.mipt_example.screens.RestaurantInfoScreen
import com.example.mipt_example.screens.RestaurantViewModel
import com.example.mipt_example.screens.SignInScreen
import com.example.mipt_example.screens.detail.DetailScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Mipt_exampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
//                  ResScreenView()
                    NavHost(navController = navController, startDestination = "SignIn") {
                        composable("SignIn") {
                            val viewModel = hiltViewModel<SignInViewModel>()
                            SignInScreen(screenViewModel = viewModel, navController = navController)
                        }

                        composable("RestaurantInfoScreen") {
                            val viewModel = hiltViewModel<RestaurantViewModel>()
                            RestaurantInfoScreen(resViewModel = viewModel, navController = navController)
                        }

                        composable("detail/{name}") { backStackEntry ->
                            DetailScreen(backStackEntry.arguments?.getString("name").orEmpty())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Mipt_exampleTheme {
        Greeting("Android")
    }
}