package com.ashutosh.bingo.Authentication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashutosh.bingo.R
import com.ashutosh.bingo.ui.theme.BingoTheme

@Composable
fun Login() {

    val context = LocalContext.current.applicationContext

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF31304D)) ,
        verticalArrangement = Arrangement.Center
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(18.dp)
                .verticalScroll(rememberScrollState()) ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Image(
                painter = painterResource(id = R.drawable.logobingo) ,
                contentDescription = null ,
                contentScale = ContentScale.Fit ,
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .height(270.dp)
                    .fillMaxWidth() ,

                )


            Text(
                text = "Sign In" ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .padding(horizontal = 30.dp) ,
                color = Color.White ,
                textAlign = TextAlign.Center ,
                fontSize = 40.sp ,
                fontWeight = FontWeight.ExtraBold

            )

            val number = "+91" + numberTextField(Icons.Outlined.Phone , "" , "+91")
            val password = Password(Icons.Outlined.Lock , "password" , "")


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 10.dp) ,
                horizontalAlignment = Alignment.End
            ) {

                Text(text = "Forgot Password?" ,
                    color = Color(0xFFCC9913) ,
                    fontSize = 12.sp ,
                    modifier = Modifier.clickable {
//                            Toast.makeText(context,"Temporary Blocked",Toast.LENGTH_LONG).show()

                    })
            }

//            Spacer(modifier = Modifier.padding(25.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {

                Button(
                    enabled = (true) ,
                    onClick = {

//                        signInScreenViewModel.loadingState = true
//
//                        val loginPost = LoginRequest(
//                            signInScreenViewModel.phoneNo ,
//                            signInScreenViewModel.password
//                        )
//
//                        signInScreenViewModel.loginUser(loginPost)

                    } ,
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFFCC9913) ,
                        disabledContainerColor = Color.DarkGray
                    ) ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp) ,
                    shape = RoundedCornerShape(
                        topEnd = 10.dp ,
                        topStart = 10.dp ,
                        bottomEnd = 10.dp ,
                        bottomStart = 10.dp
                    )
                ) {

                    AnimatedVisibility(
                        visible = true
//                        ! signInScreenViewModel.loadingState
                        ,
                        enter = fadeIn() ,
                        exit = fadeOut()
                    ) {

                        Text(
                            text = "Sign In" , color = Color.White ,
                            fontSize = 20.sp ,
                            modifier = Modifier.padding(8.dp)
                        )

                    }

                    AnimatedVisibility(
                        visible =  true
//                                signInScreenViewModel.loadingState
                        ,
                        enter = fadeIn() ,
                        exit = fadeOut()
                    ) {

//                        LottieAnimation(
//                            modifier = Modifier.size(40.dp) ,
//                            composition = composition ,
//                            progress = { progress })

                    }

                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 10.dp) ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {

                    Text(
                        text = "Don't have an Account?" ,
                        color = Color(0xFFF6F6F6) ,

                        fontSize = 12.sp
                    )

                    Text(text = "Sign Up" ,
                        color = Color(0xFFCC9913) ,

                        fontSize = 12.sp ,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .clickable {

                            })
                }
            }

        }

    }



}



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextField(icon: ImageVector , plText: String , prefixText : String): String {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }


    val containerColor = Color(0xFF222222)
    OutlinedTextField(

        value = text,
        leadingIcon = {
            Icon(
                icon, contentDescription = "icon",
                tint = Color(0xFFA7A7A7)
            )
        },
        onValueChange = { text = it },
        shape = RoundedCornerShape(10.dp) ,
        prefix = {
            Text(
                text = prefixText ,
                color = Color(0xFFF6F6F6) ,
                fontSize = 16.sp
            )
        },


        placeholder = { Text(text = plText, color = Color(0xFFA7A7A7) ,fontSize = 14.sp) },

        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        ) ,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White ,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color(0xFF161A30) ,
            unfocusedContainerColor = Color(0xFF161A30) ,
            disabledContainerColor = Color(0xFF161A30) ,
            focusedBorderColor = Color(0xFF555555) ,
            unfocusedBorderColor = Color(0xFF555555) ,
        ) ,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(top = 14.dp),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                //



            }
        ) ,

        )
    return text
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Password(icon: ImageVector , plText: String , prefixText : String): String {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }


    val containerColor = Color(0xFF222222)
    OutlinedTextField(
        value = text,
        leadingIcon = {
            Icon(
                icon, contentDescription = "icon",
                tint = Color(0xFFA7A7A7)
            )
        },
        onValueChange = { text = it },
        shape = RoundedCornerShape(10.dp) ,
        prefix = {
            Text(
                text = prefixText ,
                color = Color(0xFFF6F6F6) ,
                fontSize = 14.sp
            )
        },


        placeholder = { Text(text = plText, color = Color(0xFFA7A7A7) ,fontSize = 14.sp) },

        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        ) ,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White ,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color(0xFF161A30) ,
            unfocusedContainerColor = Color(0xFF161A30) ,
            disabledContainerColor = Color(0xFF161A30) ,
            focusedBorderColor = Color(0xFF555555) ,
            unfocusedBorderColor = Color(0xFF555555) ,
        ) ,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(top = 14.dp),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                //



            }
        ) ,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation() ,
        trailingIcon = {
            val icon = if (isPasswordVisible) R.drawable.eyeopen else R.drawable.eyeclose
            Icon(
                painterResource(id = icon) ,
                contentDescription = if (isPasswordVisible) "Hide password" else "Show password",
                tint = Color(0xFFA7A7A7) ,
                modifier = Modifier
                    .size(30.dp)
                    .clickable { isPasswordVisible = !isPasswordVisible }
            )
        }
    )
    return text
}





@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun numberTextField(icon: ImageVector , plText: String , prefixText : String): String {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }


    val containerColor = Color(0xFF222222)
    OutlinedTextField(

        value = text,
        leadingIcon = {
            Icon(
                icon, contentDescription = "icon",
                tint = Color(0xFFA7A7A7)
            )
        },
        onValueChange = { newValue ->
            // Limit input to 10 characters
            if (newValue.length <= 10) {
                text = newValue
            }
        },
        shape = RoundedCornerShape(10.dp) ,
        prefix = {
            Text(
                text = prefixText ,
                color = Color(0xFFF6F6F6) ,
                fontSize = 16.sp
            )
        },


        placeholder = { Text(text = plText, color = Color(0xFFA7A7A7) ,
            fontSize = 14.sp) },

        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        ) ,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White ,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color(0xFF161A30) ,
            unfocusedContainerColor = Color(0xFF161A30) ,
            disabledContainerColor = Color(0xFF161A30) ,
            focusedBorderColor = Color(0xFF555555) ,
            unfocusedBorderColor = Color(0xFF555555) ,
        ) ,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(top = 14.dp),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                //



            }
        ) ,

        )
    return text
}

@Preview
@Composable
fun LoginPreview() {
    BingoTheme {
        Login()
    }
}
