package com.example.mipt_example.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mipt_example.R
import com.example.mipt_example.SignInEvent
import com.example.mipt_example.SignInViewModel

//class SignIn : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            Mipt_exampleTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
//                    ScreenView()
//                }
//            }
//        }
//    }
//}

//@Preview(showBackground = true)
@Composable
fun SignInScreen(screenViewModel: SignInViewModel, navController: NavController) {
    val viewState = screenViewModel.viewState.collectAsState()

    Box(modifier = Modifier.background(Color.White)) {
        Image(painter = painterResource(id = R.drawable.pattern), contentDescription = null)
    }

    Column(
        modifier = Modifier
            .width(375.dp)
            .height(812.dp), //812
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(7.dp)) // 49
        Box( modifier = Modifier
            .background(Color.Transparent)) {
            Image(painter = painterResource(id = R.drawable.vegetables), contentDescription = null,
                Modifier
                    .width(87.dp) // 175
                    .height(69.dp)) // 139
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = stringResource(R.string.FoodNinja), color = Color(0xFF15BE77),
            textAlign = TextAlign.Center,
            modifier = Modifier, fontSize = 40.sp,
            fontWeight = FontWeight(400)
        )

        Text(text = stringResource(R.string.Deliver_Favorite_Food), textAlign = TextAlign.Center,
            modifier = Modifier.offset(y = (-6).dp), fontSize = 13.sp,
            fontWeight = FontWeight(600)
        )

        Spacer(modifier = Modifier.height(30.dp)) // 65
        Text(text = stringResource(R.string.Sign_Up_For_Free),textAlign = TextAlign.Center,
            modifier = Modifier, fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )
        ShowTextField(topPadding = 10.dp, placeholderText = stringResource(R.string.Login), // 40
            image_id = R.drawable.profile, textToWrite = viewState.value.login,
            lambdaChangeText =  {screenViewModel.obtainEvent(SignInEvent.FillLogin(it))})

        ShowTextField(topPadding = 12.dp, placeholderText = stringResource(R.string.E_Mail),
            image_id = R.drawable.message, textToWrite = viewState.value.email,
            lambdaChangeText = {screenViewModel.obtainEvent(SignInEvent.FillEmail(it))})

        Spacer(modifier = Modifier.height(12.dp))
        Box(modifier = Modifier
        ) {
            TextField(
                value = viewState.value.password,
                onValueChange = {screenViewModel.obtainEvent(SignInEvent.FillPassword(it))},
                placeholder = {
                    Text(text = stringResource(R.string.Password), color = Color(0x66000000),
                        fontSize = 14.sp, fontWeight = FontWeight(400))
                },
                modifier = Modifier
                    .width(325.dp)
                    .height(57.dp), // 57
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                ),
                leadingIcon = {
                    Image(painter = painterResource(id = R.drawable.lock),
                        contentDescription = null)
                },
                trailingIcon = {
                    val nonVisible = ImageVector.vectorResource(id = R.drawable.show)
                    val visible = ImageVector.vectorResource(id = R.drawable.eye_icon)

                    IconButton(onClick = { screenViewModel.obtainEvent(SignInEvent.ShowPassword)}) {
                        Icon(
                            imageVector = if (viewState.value.hidePass) nonVisible else visible,
                            contentDescription = if (viewState.value.hidePass)
                                stringResource(R.string.Show_Password)
                            else stringResource(R.string.Hide_Password)
                        )
                    }
                },
                visualTransformation = if (viewState.value.hidePass) VisualTransformation.None
                else PasswordVisualTransformation()
            )
        }

        Spacer(modifier = Modifier.height(20.dp)) // 20
        Column(modifier = Modifier
            .width(325.dp), horizontalAlignment = Alignment.Start) {
            Row() {
                Checkbox(
                    checked = viewState.value.keepSignInCheck,
                    modifier = Modifier
                        .width(22.dp)
                        .height(22.dp),
                    onCheckedChange = { screenViewModel.obtainEvent(SignInEvent.CheckKeepSignIn) },
                    colors = CheckboxDefaults.colors(
                        Color(0xFF53E88B)
                    )
                )
                Text(text = stringResource(R.string.Keep_Me_Signed_In),
                    textAlign = TextAlign.Center, fontSize = 12.sp,
                    fontWeight = FontWeight(400), modifier = Modifier.padding(start = 8.dp))
            }

            Spacer(modifier = Modifier.height(14.dp))
            Row(modifier = Modifier) {
                Checkbox(
                    checked = viewState.value.emailMe,
                    modifier = Modifier
                        .width(22.dp)
                        .height(22.dp),
                    onCheckedChange = { screenViewModel.obtainEvent(SignInEvent.CheckEmailMe) },
                    colors = CheckboxDefaults.colors(
                        Color(0xFF53E88B)
                    )
                )

                Text(text = stringResource(R.string.Email_Me), textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400), modifier = Modifier.padding(start = 8.dp))
            }
        }

        Spacer(modifier = Modifier.height(11.dp)) // 44
        Button(colors = ButtonDefaults.buttonColors(Color(0xFF53E88B)),
            onClick = {navController.navigate("RestaurantInfoScreen") {
                         popUpTo("SignIn") {
                            inclusive = true
                         }
                      }},
            content = {
                Text(text = stringResource(R.string.Create_Account),
                    fontWeight = FontWeight(400),
                    fontSize = 16.sp,
                    color = Color.White
                )
            },
            modifier = Modifier
                .width(175.dp)
                .height(57.dp))

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = stringResource(R.string.Already_Account),
            fontWeight = FontWeight(400),
            fontSize = 12.sp,
            modifier = Modifier
                .clickable { },
            color = Color(0xFF53E88B),
            textDecoration = TextDecoration.Underline)
    }

    val context = LocalContext.current

}

@Composable
fun ShowTextField(topPadding: Dp, placeholderText: String, image_id: Int,
                  textToWrite: String, lambdaChangeText: (String) -> Unit) {
    Spacer(modifier = Modifier.height(topPadding))
    Box(modifier = Modifier.
    padding(top = topPadding)
    ) {

        TextField(
            value = textToWrite,
            onValueChange = lambdaChangeText,
            placeholder = {
                Text(text = placeholderText, color = Color(0x66000000),
                fontSize = 14.sp, fontWeight = FontWeight(400))
            },
            modifier = Modifier
                .width(325.dp)
                .height(57.dp), // 57
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White
            ),
            leadingIcon = {
                Image(painter = painterResource(id = image_id),
                    contentDescription = null)
            }
        )
    }
}
