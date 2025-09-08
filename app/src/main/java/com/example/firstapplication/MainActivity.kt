package com.example.firstapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.firstapplication.navigation.SetupNavigation
import com.example.firstapplication.ui.screen.ProfileScreen
import com.example.firstapplication.viewModel.ProductViewModel
import com.example.firstapplication.viewModel.UserDataVM
import com.example.firstapplication.viewModel.VMFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val searchTxt = remember { mutableStateOf("") }
            val userNameTxt = remember { mutableStateOf("") }
            val phoneNumTxt = remember { mutableStateOf("") }
            val emailTxt = remember { mutableStateOf("") }

            val navController = rememberNavController()

            val factory = VMFactory(applicationContext)
            val userDataVM = ViewModelProvider(this, factory)[UserDataVM::class.java]

            val userNameTakenVM by userDataVM.userName.collectAsState()
            userNameTxt.value = userNameTakenVM

            val phoneNumTakenVM by userDataVM.phoneNum.collectAsState()
            phoneNumTxt.value = phoneNumTakenVM

            val emailTakenVM by userDataVM.email.collectAsState()
            emailTxt.value = emailTakenVM

 val productViewModel : ProductViewModel = hiltViewModel()

            SetupNavigation(
                search = searchTxt,
                userName = userNameTxt,
                phoneNum = phoneNumTxt,
                email = emailTxt,
                navController = navController,
                viewModel = productViewModel
            )
        }
    }
}

var menuExpanded = mutableStateOf(false)

