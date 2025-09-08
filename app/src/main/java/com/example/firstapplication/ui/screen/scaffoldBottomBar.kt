package com.example.firstapplication.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.firstapplication.R
import com.example.firstapplication.navigation.Screens


var bottomBarItem = listOf(
    Pair("پروفایل", R.drawable.ic_profile),
    Pair("سبد خرید",  R.drawable.ic_shopping_bag),
    Pair("دسته بندی", R.drawable.ic_category),
    Pair("خانه", R.drawable.ic_home)
)

var bottomItemState = mutableStateOf("خانه")

@Composable
fun ScaffoldBottomBar(navController: NavHostController){
    BottomAppBar(
        containerColor = Color.White,
        modifier = Modifier.shadow(14.dp)
    ){
       Row(
           modifier = Modifier
               .padding(start = 20.dp, end = 20.dp)
               .fillMaxWidth()
               .background(Color.White),
           horizontalArrangement = Arrangement.SpaceEvenly
       ) {
           for ((text , icon) in bottomBarItem) {
               Column (
                   horizontalAlignment = Alignment.CenterHorizontally,
                   modifier = Modifier
                       .height(80.dp)
                       .clickable { bottomItemState.value = text }
               ){
                   Spacer(
                       modifier =Modifier
                           .width(38.dp)
                           .height(1.dp)
                           .background(
                               if(bottomItemState.value == text) Color.Red
                               else Color.Transparent
                           )
                           .padding(top = 4.dp)
                   )

                   IconButton(

                      onClick =  {
                          bottomItemState.value = text
                          selectedItem(bottomItemState.value, navController)
                      }
                   ){
                       Icon(
                           modifier = Modifier
                               .size(24.dp),
                           painter = painterResource(icon),
                           contentDescription = null,
                           tint = Color.Unspecified
                       )
                   }
                       Text(
                           text = text,
                           color =
                               if(bottomItemState.value == text)
                                   Color.Black
                               else
                                   colorResource(R.color.text_light_gray),
                           fontWeight = if(bottomItemState.value == text)
                                            FontWeight.Bold
                            else
                                FontWeight.Normal

                       )
                   Spacer(modifier = Modifier.height(15.dp))

               }
           }
       }
    }
}


fun selectedItem(text:String, navController: NavHostController){

    when (bottomItemState.value) {
        "خانه" -> {
            navController.navigate(Screens.Home.route){
               popUpTo(Screens.Home.route) {
                 inclusive = true
               }
            }
        }

        "دسته بندی" -> {
            navController.navigate(Screens.Category.route) {
                popUpTo(Screens.Home.route) {
                    inclusive = false // خانه باقی بمونه
                }
            }
        }

        "سبد خرید"->{
            bottomItemState.value = text
            navController.navigate(Screens.SelectedProduct.route) {
                popUpTo(Screens.Home.route) {
                    inclusive = false // خانه باقی بمونه
                }
            }
      }

        "پروفایل" -> {
            navController.navigate(Screens.Profile.route) {
                popUpTo(Screens.Home.route) {
                    inclusive = false // خانه باقی بمونه
                }
            }
        }
    }
}
