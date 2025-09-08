package com.example.firstapplication.data.ds


sealed class DsName(val route: String ) {
    object userName: DsName("name")
    object phoneNum: DsName("phoneNum")
    object email: DsName("email")
}
object PrefKeys {
    const val GENDER = "gender"
    const val USER_NAME = "user_name"
    const val PHONE_NUM = "phone_number"
    const val EMAIL = "user_email"
}