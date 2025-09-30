package com.example.firstapplication.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.firstapplication.R
import com.example.firstapplication.menuExpanded
import com.example.firstapplication.navigation.Screens
import com.example.firstapplication.utils.loadProfileImage

@Composable
fun Menu(navController: NavHostController) {

    AnimatedVisibility(
        visible = menuExpanded.value,
        enter = fadeIn(animationSpec = tween(0)) +
                slideInVertically(initialOffsetY = { 20 }),
        exit = fadeOut(animationSpec = tween(0)) +
                slideOutVertically(targetOffsetY = { -20 })
    ) {
        DropdownMenu(
            expanded = true,
            onDismissRequest = { menuExpanded.value = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(bottom = 0.dp, top = 2.dp, start = 1.dp, end = 1.dp)
                .border(width = 3.dp, color = Color.Red)
                .padding(horizontal = 16.dp),
            offset = DpOffset(x = 0.dp, y = 20.dp)
        ) {
            MenuBody(navController)
        }
    }
}

@Composable
fun MenuBody(navController: NavHostController) {
    val menuItems = listOf(
        Pair("پروفایل کاربری", R.drawable.ic_profile),
        Pair("سفارشات من (به زودی)", R.drawable.ic_order),
        Pair("بازخورد", R.drawable.ic_support),
        Pair("قوانین و مقررات (به زودی)", R.drawable.ic_rules),
        Pair(" درباره ما (به زودی)", R.drawable.ic_about_us),
        Pair("ارتباط با ما (به زودی)", R.drawable.ic_contact_us)
    )
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf(loadProfileImage(context)) }

    Box(
        modifier = Modifier
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Row(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(
                        colorResource(R.color.light_cream),
                        shape = RoundedCornerShape(16.dp)
                    ),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically


            ) {
                Text(
                    text = "user name",
                    fontWeight = FontWeight.Bold
                )
                Image(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(45.dp),
                    bitmap = bitmap.value.asImageBitmap(),
                    contentDescription = null
                )
            }
            for ((text, icon) in menuItems) {

                Row(
                    Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(),
                            onClick = { onMenuItemClick(text, navController) }
                        )
                        .fillMaxWidth()
                        .background(colorResource(colorItems(icon))),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = text,
                        modifier = Modifier
                            .padding(12.dp)
                            .background(Color.White),
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(
                        { onMenuItemClick(text, navController) }) {

                        Icon(
                            painter = painterResource(icon),
                            contentDescription = null,
                            modifier = Modifier.size(28.dp),
                            tint = Color.Unspecified
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

fun onMenuItemClick(label: String, navController: NavHostController) {

    when (label) {
        "پروفایل کاربری" -> {
            navController.navigate(Screens.Profile.route) {
                popUpTo(Screens.Home.route) {
                    inclusive = false
                }
                menuExpanded.value = false
            }
        }

        "سفارشات من" -> {
        }

        "بازخورد" -> {
            navController.navigate(Screens.Feedback.route) {
                popUpTo(Screens.Home.route) {
                    inclusive = false
                }
            }
            menuExpanded.value = false
        }

        "قوانین و مقررات " -> {
        }

        "درباره ما" -> {
        }

        "ارتباط با ما" -> {
        }

    }
}

fun colorItems(icon: Int): Int {
    when (icon) {
        R.drawable.ic_profile -> return R.color.white
        R.drawable.ic_order -> return R.color.text_light_gray
        R.drawable.ic_support -> return R.color.white
        R.drawable.ic_rules -> return R.color.text_light_gray
        R.drawable.ic_about_us -> return R.color.text_light_gray
        R.drawable.ic_contact_us -> return R.color.text_light_gray
    }
    return R.color.white
}


