package com.example.firstapplication.ui.screen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.firstapplication.R
import com.example.firstapplication.menuExpanded
import com.example.firstapplication.navigation.Screens
import com.example.firstapplication.utils.Width
import com.example.firstapplication.utils.loadProfileImage
import com.example.firstapplication.viewModel.ProfileImageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTopBar(navController: NavHostController,
                   viewModel: ProfileImageViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf(loadProfileImage(context).asImageBitmap()) }

    val recompuseTrigger = viewModel.recomposeTrigger.collectAsState()
    LaunchedEffect(recompuseTrigger.value){
        bitmap.value = loadProfileImage(context).asImageBitmap()
    }
//    val context = LocalContext.current
//
//    val recomposeTrigger = viewModel.recomposeTrigger.collectAsState()
//
//    val bitmap = remember(recomposeTrigger.value) {
//        derivedStateOf {
//            loadProfileImage(context).asImageBitmap()
//        }
//    }

    TopAppBar(
        title = {},

        navigationIcon = { TopBarNavigation(navController) },

        actions = {
            TopBarAction(viewModel,bitmap)
            Menu(navController)
        }
    )
}

@Composable
fun TopBarNavigation(navController: NavHostController){
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        IconButton({
            navController.navigate(Screens.Home.route){
                popUpTo(Screens.Home.route) {
                    inclusive = true
                }
            }
        },
            modifier = Modifier
                .padding(end = 0.dp, start = 24.dp)
                .size(36.dp) ){
            Icon(
                modifier = Modifier
                    .scale(1.3f),
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = "back"
            )
        }
        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = "Icon online shop "
        )
        Image(
            painter = painterResource(id = R.drawable.img_online_shop),
            contentDescription = "Icon online shop "
        )
    }
}

@Composable
fun TopBarAction(viewModel: ProfileImageViewModel ,
                 bitmap: MutableState<ImageBitmap>
){

    Row (
        verticalAlignment = Alignment.CenterVertically
    ){

        Image(
            bitmap = bitmap.value,
            modifier = Modifier
                .size(32.dp)
                .padding(end = 4.dp ),
            contentDescription= "Profile photo"
        )
        IconButton(
            onClick = {},
            modifier = Modifier
                .size(38.dp)
                .padding(0.dp)
        ) {
            Icon(
                modifier = Modifier
                    .scale(1.2f),
                painter = painterResource(R.drawable.ic_notif),
                contentDescription = "notification",
                tint = Color.Unspecified
            )
        }
        Width(8)
        IconButton(
            {
            menuExpanded.value = !menuExpanded.value
            },
            modifier = Modifier
                .size(40.dp)
                .padding(end= 20.dp)
        ) {
            Crossfade(targetState = menuExpanded) { expanded ->
                if (expanded.value) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "closeMenu",
                        modifier = Modifier.scale(1.2f),
                        tint = Color.Unspecified
                    )
                } else {
                    Icon(
                        painter = painterResource(R.drawable.ic_menu),
                        contentDescription = "menu",
                        modifier = Modifier.scale(1.2f),
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }
}

