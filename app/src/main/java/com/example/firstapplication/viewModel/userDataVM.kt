package com.example.firstapplication.viewModel

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapplication.data.ds.PrefKeys.EMAIL
import com.example.firstapplication.data.ds.PrefKeys.PHONE_NUM
import com.example.firstapplication.data.ds.PrefKeys.USER_NAME
import com.example.firstapplication.data.ds.dataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class UserDataVM(private val context: Context) : ViewModel() {
    private val _userName = MutableStateFlow("")
    private val _phoneNum = MutableStateFlow("")
    private val _email = MutableStateFlow("")
    val userName: StateFlow<String> = _userName
    val phoneNum: StateFlow<String> = _phoneNum
    val email: StateFlow<String> = _email

    init {
        viewModelScope.launch {
            context.dataStore.data
                .map { prefs ->
                    prefs[stringPreferencesKey(USER_NAME)] ?: ""
                }
                .collect {
                    _userName.value = it
                }
        }
        viewModelScope.launch {
            context.dataStore.data
                .map { prefs ->
                    prefs[stringPreferencesKey(PHONE_NUM)] ?: ""
                }
                .collect {
                    _phoneNum.value = it
                }
        }
        viewModelScope.launch {
            context.dataStore.data
                .map { prefs ->
                    prefs[stringPreferencesKey(EMAIL)] ?: ""
                }
                .collect {
                    _email.value = it
                }
        }
    }
}