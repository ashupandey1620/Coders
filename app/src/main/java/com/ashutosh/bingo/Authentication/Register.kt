package com.ashutosh.bingo.Authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashutosh.bingo.R
import com.ashutosh.bingo.ui.theme.BingoTheme


@Composable
fun Register(onBack: () -> Unit,
             onClickRegister: () -> Unit,
             onClickLogin: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF31304D)),
        Arrangement.Center
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
                text = "Register" ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp) ,
                color = Color.White ,
                textAlign = TextAlign.Center ,
                fontSize = 40.sp ,
                fontWeight = FontWeight.ExtraBold)

            val email = TextField(Icons.Outlined.Person , "Your Email" , "")

            val username = numberTextField(Icons.Outlined.Phone , "Enter Your Username" , "")






            val password = Password(Icons.Outlined.Lock , "Your Password" , "")
            val cnfpassword = Password(Icons.Outlined.Lock , "Confirm Password" , "")


            Spacer(modifier = Modifier.padding(25.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {

                Button(enabled = true
                    ,
                    onClick = {
                              onClickRegister()
                    } ,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCC9913) ,
                        disabledContainerColor = Color.DarkGray) ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp) ,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Register" , color = Color.White ,
                        fontSize = 20.sp ,
                        modifier = Modifier.padding(8.dp)
                    )
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
                        text = "Already have an Account?" ,
                        color = Color(0xFFF6F6F6) ,
                        fontSize = 12.sp
                    )

                    Text(text = "Sign In" ,
                        color = Color(0xFFCC9913) ,
                        fontSize = 12.sp ,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .clickable {
                                onClickLogin()
                            })
                }
            }

        }


    }


}

@Preview
@Composable
fun RegisterPreview() {
    BingoTheme {
        Register({},{},{})
    }
}
