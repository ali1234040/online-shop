package com.example.firstapplication.ui.screen

import android.graphics.Bitmap
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.firstapplication.utils.PermissionState
import com.example.firstapplication.utils.loadProfileImage
import com.example.firstapplication.utils.saveProfileImage
import com.example.firstapplication.viewModel.ProfileImageViewModel

//@Composable
//fun ProfileImageTest(
//    viewModel: ProfileImageViewModel = hiltViewModel(),
//    bitmap: Bitmap
//){
//    val context = LocalContext.current
//
//
//
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ){
//            Button(onClick = {
//                viewModel.checkPermission(android.Manifest.permission.CAMERA)
//            }) {
//                Text("camera")
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//            Image(
//                bitmap = bitmap.asImageBitmap(),
//                contentDescription = "Profile Image",
//                modifier = Modifier
//                    .size(258.dp)
//                    .clip(CircleShape)
//            )
//        }
//    }
//}
//@Composable
//fun setProfilePhoto(
//    viewModel: ProfileImageViewModel = hiltViewModel()
//) {
//
//
//
//
//}
//
//@Composable
//fun TakePhoto(viewModel: ProfileImageViewModel = hiltViewModel()) {
//
//
//}
//////////////////////

//@Composable
//fun SetProfilePhoto(
//    viewModel: ProfileImageViewModel = hiltViewModel()
//) {
//
//    val context = LocalContext.current
//
//    val bitmap = remember { mutableStateOf<Bitmap>(loadProfileImage(context)) }
//
//    val launcher = rememberLauncherForActivityResult(
//        ActivityResultContracts.RequestPermission()
//    ){ granted ->
//        if (granted) viewModel.checkPermission(android.Manifest.permission.CAMERA)
//    }
//
//    val cameraLauncher = rememberLauncherForActivityResult(
//        ActivityResultContracts.TakePicturePreview()
//    ) { newBitmap ->
//        newBitmap?.let {
//            bitmap.value = it
//        }
//    }
//
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ){
//            Button(onClick = {
//                viewModel.checkPermission(android.Manifest.permission.CAMERA)
//            }) {
//                Text("camera")
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//            Image(
//                bitmap = bitmap.value.asImageBitmap(),
//                contentDescription = "Profile Image",
//                modifier = Modifier
//                    .size(258.dp)
//                    .clip(CircleShape)
//            )
//        }
//    }
//
//    LaunchedEffect(bitmap.value){
//        saveProfileImage(context, bitmap.value)
//    }
//
//    val state = viewModel.state.collectAsState()
//    val shouldCameraOpenKey = viewModel.shouldCameraOpenKey.collectAsState()
//    LaunchedEffect(shouldCameraOpenKey.value) {
//        when (state.value) {
//            PermissionState.GRANTED -> {
//                Log.i("TAG", "granted")
//                cameraLauncher.launch(null)
//            }
//
//            PermissionState.NEED_REQUEST -> {
//                Log.i("TAG", "need")
//                launcher.launch(android.Manifest.permission.CAMERA)
//            }
//
//            PermissionState.LOADING -> {
//                Log.i("TAG", "در حال بررسی مجوز...")
//            }
//        }
//    }
//}
//
//
//
