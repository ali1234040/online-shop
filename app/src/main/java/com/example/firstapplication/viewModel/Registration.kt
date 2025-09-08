package com.example.firstapplication.viewModel

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.firstapplication.data.ds.DsName
import com.example.firstapplication.data.ds.PrefKeys.EMAIL
import com.example.firstapplication.data.ds.PrefKeys.GENDER
import com.example.firstapplication.data.ds.PrefKeys.PHONE_NUM
import com.example.firstapplication.data.ds.PrefKeys.USER_NAME
import com.example.firstapplication.data.ds.dataStore


suspend fun setName(context: Context, username: String) {
    context.dataStore.edit { prefs ->
        prefs[stringPreferencesKey(USER_NAME)] = username
    }
}

suspend fun setPhoneNum(context: Context, phoneNum: String) {
    context.dataStore.edit { prefs ->
        prefs[stringPreferencesKey(PHONE_NUM)] = phoneNum
    }
}

suspend fun setEmail(context: Context, email: String) {
    context.dataStore.edit { prefs ->
        prefs[stringPreferencesKey(EMAIL)] = email
    }

}






