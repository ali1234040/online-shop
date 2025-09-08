package com.example.firstapplication.ui.screen

import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.firstapplication.R
import com.example.firstapplication.navigation.Screens

@Composable
fun CategoryScreen(navController: NavHostController) {
    Scaffold(
        topBar = { ScaffoldTopBar(navController) },
        bottomBar = { ScaffoldBottomBar(navController) }
    ){ padding ->
        Box(
            modifier = Modifier
                .background(color = colorResource(id = R.color.light_cream))
        ) {
            Column {
                Spacer(modifier = Modifier.height(padding.calculateTopPadding()))
                CategoryContent()
            }
        }

    }
}

@Composable
fun CategoryContent(){
    Column (
        modifier = Modifier.fillMaxSize()
    ){

        Spacer(modifier = Modifier.height(30.dp))

        Box(){

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .padding(24.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                colorResource(R.color.light_green),
                                colorResource(R.color.dark_green)
                            )
                        )
                    )

            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier
                            .size(220.dp)
                            .padding(end = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "از ورزشت لذت ببر!",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(3.dp),
                            style = TextStyle(
                                textDirection = TextDirection.Rtl,
                                textAlign = TextAlign.Center
                            ),
                            color = Color.White,
                            fontSize = 22.sp
                        )
                        Text(
                            text = "تولید و عرضه انواع کفش های اسپرت در همه سایز ها",
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                textDirection = TextDirection.Rtl
                            ),
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.img_shoes_ad),
                        contentDescription = null,
                        modifier = Modifier
                            .size(160.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 54.dp, top = 10.dp )
                    .clip(RoundedCornerShape(32.dp))
            ){
                Text(
                    text = "تخفیف ویژه",
                    modifier = Modifier
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    colorResource(R.color.light_orange),
                                    colorResource(R.color.dark_orange)
                                )
                            )
                        )
                        .padding(start = 10.dp, end = 10.dp, bottom = 4.dp, top = 4.dp),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "دسته بندی",
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 24.dp, bottom = 10.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.End
        )

        Column(){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ){

                firstThreeCItems.forEach{ (text, img) ->
                    Box(
                        modifier = Modifier
                            .size(130.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Spacer(modifier = Modifier.height(10.dp))
                            Image(
                                painter = painterResource(img) ,
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(1.4f)
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = text,
                                modifier = Modifier
                                    .height(20.dp)
                                    .width(50.dp),
                                textAlign = TextAlign.Center,
                                color = Color.Black

                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                secondThreeCItems.forEach{ (text, img) ->
                    Box(
                        modifier = Modifier
                            .size(130.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Spacer(modifier = Modifier.height(10.dp))
                            Image(
                                painter = painterResource(img) ,
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(1.4f)
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = text,
                                modifier = Modifier
                                    .height(20.dp)
                                    .width(50.dp),
                                textAlign = TextAlign.Center,
                                color = Color.Black

                            )
                        }
                    }
                }
            }
        }
    }
}

val categoryItems = listOf(
    Pair("دخترانه", R.drawable.img_girlish_dress),
    Pair("مردانه", R.drawable.img_manly_tshirt),
    Pair("زنانه", R.drawable.img_women_clothing),
    Pair("کفش", R.drawable.img_shoes),
    Pair("نوزادی", R.drawable.img_baby_dress),
    Pair("پسرانه", R.drawable.img_boyish_tshirt)
)

val firstThreeCItems = categoryItems.take(3)
val secondThreeCItems = categoryItems.drop(3).take(3)