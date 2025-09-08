package com.example.firstapplication.ui.screen

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.firstapplication.R
import com.example.firstapplication.utils.Height
import com.example.firstapplication.utils.Width
import com.example.firstapplication.viewModel.ProductViewModel


@Composable
fun SelectedProductScreen(
    navController: NavHostController,
    viewModel: ProductViewModel
) {

    Scaffold(
        topBar = { ScaffoldTopBar(navController) },
        bottomBar = { ScaffoldBottomBar(navController) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.light_cream))
        ) {
            val selectedProduct = viewModel.selectedProducts
            Column {
                Spacer(modifier = Modifier.height(padding.calculateTopPadding()))
                Height(16)
                Text(
                    ":لیست خرید",
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                        .padding(end = 16.dp, bottom = 16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(selectedProduct) { _, product ->
                        SelectedProductCards(img = product.imageUrl, price = product.price)
                    }
                }
            }
        }
    }
}

@Composable
fun SelectedProductCards(img: Int, price: Int) {
    Box(
        modifier = Modifier
            .padding(horizontal = 7.dp)
            .background(
                colorResource(R.color.light_gray),
                shape = RoundedCornerShape(12.dp)
            )

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(12.dp))
                    .background(
                        shape = RoundedCornerShape(12.dp),
                        color = Color.White,
                    )
                    .padding(8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.background(colorResource(R.color.white))
                ) {
                    Height(6)
                    Text(
                        "ست سویشرت و شلوار مردانه",
                        textAlign = TextAlign.End,
                        modifier = Modifier,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp

                    )
                    Height(10)
                    Text(
                        text = "$price تومان ",
                        textAlign = TextAlign.End,
                        modifier = Modifier,
                        color = colorResource(R.color.black),
                        fontSize = 12.sp
                    )
                }
            }
            Width(5)
            Image(
                painter = painterResource(id = img),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
            Width(15)
        }
    }
}


////        Box(
////            modifier = Modifier
////                .fillMaxSize()
////                .background(color = colorResource(R.color.light_cream))
////        ) {
////            Row(
////                modifier =  Modifier
////                    .background(Color.Black)
////                    .height(200.dp)
////            ) {
////                val selectedProduct = viewModel.selectedProducts
////
////                selectedProduct.forEach() { product ->
////                    Cards(img = product.imageUrl, price = product.price){}
////                }
////            }
////            Column(
////                modifier = Modifier.fillMaxSize()
////            ) {
//
//
////
////                Row(
////                    modifier = Modifier
////                        .background(Color.Black)
////                        .height(200.dp)
////                ) {
////                    val selectedProduct = viewModel.selectedProducts
////
////                    selectedProduct.forEach() { product ->
////                        Cards(img = product.imageUrl, price = product.price) {
//////                            viewModel.toggleProductSelection(product)
////                        }
////                    }
////                }
////
////                val products by viewModel.products.collectAsState()
////
////                LazyColumn(
////                    modifier = Modifier.padding(horizontal = 12.dp),
//////                    reverseLayout = true
////                ) {
////
////                    itemsIndexed(products) { _, product ->
////                        Cards(img = product.imageUrl, price = product.price) {
////                            viewModel.toggleProductSelection(product)
////                        }
////                    }
////                }
////            }
////        }