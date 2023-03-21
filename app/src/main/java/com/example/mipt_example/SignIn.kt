package com.example.mipt_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mipt_example.ui.theme.Mipt_exampleTheme

class SignIn : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mipt_exampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    ScreenView()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenView() {
    Box(modifier = Modifier.background(Color.White)) {
        Image(painter = painterResource(id = R.drawable.pattern), contentDescription = null)
    }

    Column(
        modifier = Modifier
            .width(375.dp)
            .height(812.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box( modifier = Modifier
            .background(Color.Transparent)
            .padding(top = 49.dp)) {
            Image(painter = painterResource(id = R.drawable.vegetables), contentDescription = null,
                Modifier
                    .width(175.dp)
                    .height(139.dp))
        }

        Text(text = "FoodNinja", color = Color(0xFF15BE77), textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 2.dp), fontSize = 40.sp,
            fontWeight = FontWeight(400)
        )

        Text(text = "Deliver Favorite Food", textAlign = TextAlign.Center,
            modifier = Modifier.offset(y = (-6).dp), fontSize = 13.sp,
            fontWeight = FontWeight(600)
        )

        Text(text = "Sign Up For Free",textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 65.dp), fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )

        ShowTextField(topPadding = 40.dp, placeholderText = "Login",
            image_id = R.drawable.profile)

        ShowTextField(topPadding = 12.dp, placeholderText = "E-Mail",
            image_id = R.drawable.message)

        Box(modifier = Modifier.
        padding(top = 12.dp)
        ) {
            var writePlaceHolder by remember {
                mutableStateOf("")
            }

            var showPassword by remember {
                mutableStateOf(false)
            }

            TextField(
                value = writePlaceHolder,
                onValueChange = { writePlaceHolder = it },
                placeholder = {
                    Text(text = "Password", color = Color(0x66000000),
                        fontSize = 14.sp, fontWeight = FontWeight(400))
                },
                modifier = Modifier
                    .width(325.dp)
                    .height(57.dp),
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

                    IconButton(onClick = {showPassword = !showPassword}) {
                        Icon(
                            imageVector = if (showPassword) nonVisible else visible,
                            contentDescription = if (showPassword) "Show Password" else "Hide Password"
                        )
                    }
                },
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
            )
        }

        Column(modifier = Modifier
            .padding(top = 20.dp)
            .width(325.dp), horizontalAlignment = Alignment.Start) {
            Row() {
                var checked by remember {
                    mutableStateOf(true)
                }
                Checkbox(
                    checked = checked,
                    modifier = Modifier
                        .width(22.dp)
                        .height(22.dp),
                    onCheckedChange = { checked = !checked },
                    colors = CheckboxDefaults.colors(
                        Color(0xFF53E88B)
                    )
                )

                Text(text = "Keep Me Signed In", textAlign = TextAlign.Center, fontSize = 12.sp,
                    fontWeight = FontWeight(400), modifier = Modifier.padding(start = 8.dp))
            }

            Row(modifier = Modifier.padding(top = 14.dp)) {
                var checked by remember {
                    mutableStateOf(true)
                }
                Checkbox(
                    checked = checked,
                    modifier = Modifier
                        .width(22.dp)
                        .height(22.dp),
                    onCheckedChange = { checked = !checked },
                    colors = CheckboxDefaults.colors(
                        Color(0xFF53E88B)
                    )
                )

                Text(text = "Email Me About Special Pricing", textAlign = TextAlign.Center, fontSize = 12.sp,
                    fontWeight = FontWeight(400), modifier = Modifier.padding(start = 8.dp))
            }
        }

        Button(colors = ButtonDefaults.buttonColors(Color(0xFF53E88B)),
            onClick = {},
            content = {
                Text(text = "Create Account",
                    fontWeight = FontWeight(400),
                    fontSize = 16.sp,
                    color = Color.White
                )
            },
            modifier = Modifier
                .padding(top = 44.dp)
                .width(175.dp)
                .height(57.dp))

        Text(text = "already have an account?",
            fontWeight = FontWeight(400),
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 20.dp).clickable {  },
            color = Color(0xFF53E88B),
            textDecoration = TextDecoration.Underline)
    }

}

@Composable
fun ShowTextField(topPadding: Dp, placeholderText: String, image_id: Int) {
    Box(modifier = Modifier.
    padding(top = topPadding)
    ) {
        var writePlaceHolder by remember {
            mutableStateOf("")
        }

        TextField(
            value = writePlaceHolder,
            onValueChange = { writePlaceHolder = it },
            placeholder = {
                Text(text = placeholderText, color = Color(0x66000000),
                fontSize = 14.sp, fontWeight = FontWeight(400))
            },
            modifier = Modifier
                .width(325.dp)
                .height(57.dp),
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