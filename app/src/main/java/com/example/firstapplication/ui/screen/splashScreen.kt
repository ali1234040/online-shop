package com.example.firstapplication.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.firstapplication.R
import com.example.firstapplication.navigation.Screens
import kotlinx.coroutines.delay

import kotlin.time.Duration.Companion.seconds

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(2.seconds)
        navController.navigate( Screens.Login.route) {
            popUpTo(Screens.Splash.route) { inclusive = true }
        }
    }

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
        Column {
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
        }
    }
}