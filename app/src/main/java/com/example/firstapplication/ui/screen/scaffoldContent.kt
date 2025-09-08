package com.example.firstapplication.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.firstapplication.R
import com.example.firstapplication.utils.Height
import com.example.firstapplication.viewModel.ProductViewModel

@Composable
fun TopList(state: MutableIntState) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Height(10)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically,
            reverseLayout = true
        ) {

            items(6) { item ->
                SelectableImage(item, state)
            }
        }
        Height(10)
    }
}

@Composable
fun SelectableImage(
    item: Int,
//  imageRes: Int,
    state: MutableIntState
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .then(
                if (state.intValue == item) Modifier.border(
                    2.dp,
                    Color.Red,
                    RoundedCornerShape(20.dp)
                ) else Modifier
            )
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .wrapContentSize()
            .padding(8.dp)
            .clickable { state.intValue = item }
    ) {
        if (state.intValue == item)
            Image(
                painter = painterResource(id = R.drawable.img_pants),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        else
            Image(
                painter = painterResource(id = R.drawable.img_pants),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
    }
}

@Composable
fun Body() {
    Column {
        Height(20)
        Text(
            text = "بلوز و پیراهن مردانه",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(end = 20.dp, bottom = 6.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.End
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp)
        ) {

        }
    }
}

@Composable
fun ProductCards(viewModel: ProductViewModel = viewModel()) {

    val products by viewModel.products.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(products) { _, product  ->
            Cards(img = product.imageUrl, price = product.price){}
        }
        //برای پر کردن ارتفاع bottomAppBar
        items(2) {
            Box(modifier = Modifier.size(80.dp))
        }
    }
}

@Composable
fun Cards(img : Int, price : Int, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(22.dp))
            .background(colorResource(R.color.light_gray))
            .size(width = 200.dp, height = 260.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )

            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(colorResource(R.color.light_gray))
                    .padding(bottom = 15.dp)

            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(12.dp))
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(12.dp)
                        )
                ){
                    Column(
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                    ) {

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            "ست سویشرت و شلوار مردانه",
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "$price تومان ",
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = colorResource(R.color.black)
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                    }

                }

                Box(
                    modifier = Modifier
                        .size(190.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .clip(CircleShape)
                            .size(24.dp)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        colorResource(R.color.light_orange),
                                        colorResource(R.color.dark_orange)
                                    )
                                ),
                                shape = CircleShape
                            )
                            .align(Alignment.CenterStart)
                            .graphicsLayer { clip = false }
                            .zIndex(2f)
                    ) {
                        IconButton(
                            onClick = onClick
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_plus),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun TitleTxtAndTxtField(
                        title: String,
                        textFieldtxt: MutableState<String>,
                        text: String,
                        icon: Painter ?= null,
                        fontSize: TextUnit = 22.sp,

                        ) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = title,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(end = 24.dp)
        )
        TextField(
            value = textFieldtxt.value,
            onValueChange = { textFieldtxt.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 10.dp),
            textStyle = TextStyle(
                textDirection = TextDirection.Rtl,
            ),
            placeholder = {
                Text(
                    text = text,
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        textDirection = TextDirection.Rtl
                    )
                )
            },
            trailingIcon =
                icon?.let {
                    {
                        Icon(
                            painter = it,
                            contentDescription = "search Icon",
                            tint = Color.Unspecified
                        )
                    }
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(16.dp)


        )
    }
}

@Composable
fun CustomButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        colorResource(id = R.color.light_orange),
                        colorResource(id = R.color.dark_orange)
                    )
                )
            )
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 20.sp
        )
    }
}
