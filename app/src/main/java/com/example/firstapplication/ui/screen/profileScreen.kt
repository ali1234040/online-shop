package com.example.firstapplication.ui.screen

import android.app.Activity
import android.util.Log
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.firstapplication.R
import com.example.firstapplication.utils.Height
import com.example.firstapplication.utils.PermissionState
import com.example.firstapplication.utils.Width
import com.example.firstapplication.utils.loadProfileImage
import com.example.firstapplication.utils.saveProfileImage
import com.example.firstapplication.viewModel.ProfileImageViewModel
import com.example.firstapplication.viewModel.setEmail
import com.example.firstapplication.viewModel.setName
import com.example.firstapplication.viewModel.setPhoneNum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navController: NavHostController,
    userName: MutableState<String>,
    phoneNum: MutableState<String>,
    email: MutableState<String>,
    viewModel: ProfileImageViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = { ScaffoldTopBar(navController,viewModel) },
        bottomBar = { ScaffoldBottomBar(navController) }
    ) { padding ->
        Box(
            modifier = Modifier
                .background(color = colorResource(id = R.color.light_cream))
        ) {
            Column {
                Spacer(modifier = Modifier.height(padding.calculateTopPadding()))
                ProfileContent(
                    userName = userName,
                    phoneNum = phoneNum,
                    email = email,
                    viewModel = viewModel
                )
            }
        }

    }
}

@Composable
fun ProfileContent(
    userName: MutableState<String>,
    phoneNum: MutableState<String>,
    email: MutableState<String>,
    viewModel: ProfileImageViewModel
) {

    val scrollState = rememberScrollState()

    val context = LocalContext.current

    val bitmap = remember { mutableStateOf(loadProfileImage(context)) }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) viewModel.checkPermission(android.Manifest.permission.CAMERA)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { newBitmap ->
        newBitmap?.let {
            bitmap.value = it
        }
    }
    LaunchedEffect(bitmap.value){
        saveProfileImage(context, bitmap.value)
        viewModel.triggerTopBarRecompose()
    }

    val state = viewModel.state.collectAsState()
    val shouldCameraOpenKey = viewModel.shouldCameraOpenKey.collectAsState()
    LaunchedEffect(shouldCameraOpenKey.value) {
        when (state.value) {
            PermissionState.GRANTED -> {
                Log.i("TAG", "granted")
                cameraLauncher.launch(null)

            }

            PermissionState.NEED_REQUEST -> {
                Log.i("TAG", "need")
                launcher.launch(android.Manifest.permission.CAMERA)
            }

            PermissionState.LOADING -> {
                Log.i("TAG", "در حال بررسی مجوز...")
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .shadow(
                        10.dp,
                        shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp),
                        clip = false
                    )
                    .background(
                        shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp),
                        brush = Brush.linearGradient(
                            colors = listOf(
                                colorResource(R.color.light_orange),
                                colorResource(R.color.dark_orange)
                            )
                        )
                    )
                    .height(200.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "پروفایل کاربری",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )

                    Height(20)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Width(5)

                        Image(
                            modifier = Modifier
                                .size(40.dp),
                            painter = painterResource(R.drawable.img_edit),
                            contentDescription = null
                        )

                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .shadow(
                                    elevation = 14.dp,
                                    shape = CircleShape,
                                    clip = false
                                )
                                .background(Color.White, CircleShape)
                                .padding(6.dp)
                        ) {
                            Image(
                                bitmap = bitmap.value.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Image(
                            modifier = Modifier
                                .size(40.dp)
                                .clickable { viewModel.checkPermission(android.Manifest.permission.CAMERA) },
                            painter = painterResource(R.drawable.img_camera),
                            contentDescription = null
                        )
                        Width(5)
                    }

                }
            }

            Height(30)

            TitleTxtAndTxtField(title = " :نام و نام خانوادگی",
                textFieldtxt = userName,
                text = " نام و نام خانوادگی خود را وارد کنید",
                )

            Height(10)

            TitleTxtAndTxtField(title = ":شماره همراه",
               textFieldtxt =  phoneNum,
                text = "شماره همراه خود را وارد کنید")

            Height(15)

            TitleTxtAndTxtField(title = ":ایمیل",
                textFieldtxt = email,
                text = "ایمیل خود را وارد کنید")

            Height(10)

            Height(20)
            CustomButton("ثبت تغییرات") {
                CoroutineScope(Dispatchers.IO).launch {
                    setName(context, userName.value)
                    setPhoneNum(context, phoneNum.value)
                    setEmail(context, email.value)
                }
            }

        Height(105)
        }
    }

}


