package com.example.firstapplication.ui.screen

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.firstapplication.R
import com.example.firstapplication.utils.Height
import com.example.firstapplication.viewModel.ProductViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    search: MutableState<String>, navController: NavHostController,
    viewModel: ProductViewModel
) {
    Scaffold(
        topBar = { ScaffoldTopBar(navController) },
        bottomBar = { ScaffoldBottomBar(navController) }
    ) { padding ->
        Column {
            Spacer(modifier = Modifier.height(padding.calculateTopPadding()))
            HomeContent(search, viewModel)

        }
    }
}

@Composable
fun HomeContent(
    search: MutableState<String>,
    viewModel: ProductViewModel
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .background(colorResource(R.color.light_cream))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Height(15)

            Box() {

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
                        .padding(start = 54.dp, top = 10.dp)
                        .clip(RoundedCornerShape(32.dp))
                ) {
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
            Height(10)

            TitleTxtAndTxtField(
                title = "!تنها با یک کلیک خرید کن",
                textFieldtxt = search,
                text = "هرچی میخوای جستجو کن...",
                icon = painterResource(R.drawable.ic_search)
            )
            Height(18)
            Text(
                text = "پرفروش ترین ها",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(end = 24.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.End
            )
            Height(10)
            val products by viewModel.products.collectAsState()

            LazyRow(
                modifier = Modifier.padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                reverseLayout = true
            ) {

                itemsIndexed(products) { _, product ->
                    Cards(img = product.imageUrl, price = product.price) {
                        viewModel.toggleProductSelection(product)
                    }
                }
            }
            Height(100)
        }
    }
}
