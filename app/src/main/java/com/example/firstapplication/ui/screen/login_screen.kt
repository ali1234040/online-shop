package com.example.firstapplication.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.firstapplication.R
import com.example.firstapplication.navigation.Screens
import com.example.firstapplication.utils.Height
import com.example.firstapplication.viewModel.setName
import com.example.firstapplication.viewModel.setPhoneNum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(userName: MutableState<String>,
                phoneNum: MutableState<String>,
                navController: NavHostController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.light_cream)),
        contentAlignment = Alignment.Center

    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            Canvas(
                modifier = Modifier
                    .size(400.dp)
                    .background(
                        colorResource(R.color.light_cream)
                    )
            ) {
                drawArc(
                    color = Color(0xFFFCEDDF),
                    startAngle = 270f,
                    sweepAngle = 90f,
                    useCenter = true,
                    topLeft = Offset(-size.width / 2, size.height / 2),
                    size = Size(size.width, size.height)
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            Canvas(
                modifier = Modifier
                    .size(400.dp)
                    .background(
                        colorResource(R.color.light_cream)
                    )
            ) {
                drawArc(
                    color = Color(0xFFFCEDDF),
                    startAngle = 90f,
                    sweepAngle = 90f,
                    useCenter = true,
                    topLeft = Offset(size.width / 2, -size.height / 2),
                    size = Size(size.width, size.height)
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Canvas(
                modifier = Modifier
                    .size(height = 300.dp, width = 200.dp)
                    .background(
                        colorResource(R.color.light_cream)
                    )
            ) {
                drawArc(
                    color = Color(0xFFFCEDDF),
                    startAngle = 270f,
                    sweepAngle = 180f,
                    useCenter = true,
                    topLeft = Offset(-size.width/4, size.height-size.width/2),
                    size = Size(size.width/2, size.width/2)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_logo),
                contentDescription = "Icon online shop ",
                modifier = Modifier.size(200.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.img_online_shop),
                contentDescription = "Icon online shop ",
                modifier = Modifier
                    .height(100.dp)
                    .width(200.dp)
            )
            Height(30)
            Text(
                text = "جهت ورود به فروشگاه اینترنتی آنلاین شاپ نام و شماره خود را وارد کرده",
                fontSize = 14.sp,
                modifier =Modifier
                    .padding(vertical = 24.dp),
                color = colorResource(R.color.text_light_gray)
            )
            Height(20)
            TitleTxtAndTxtField(
                title = ":نام کاربری",
                textFieldtxt = userName ,
                text = "نام و نام خانوادگی خود را وارد کنید ",
                fontSize = 16.sp)
            Height(15)
            TitleTxtAndTxtField(
                title = ":شماره همراه",
                textFieldtxt = phoneNum,
                text = "شماره همراه خود را وارد کنید",
                fontSize = 16.sp)
            Height(15)
            val context = LocalContext.current
            CustomButton("تایید") {
                CoroutineScope(Dispatchers.IO).launch {
                   setName(context, userName.value)
                   setPhoneNum(context, phoneNum.value)
                }
                navController.navigate(Screens.Home.route){
                    popUpTo(0){ inclusive = true }
                }
            }
        }
    }
}