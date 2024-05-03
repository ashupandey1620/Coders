package com.ashutosh.bingo.NetworkModel.Profile.Logout

data class LogoutResponse(
    val `data`: Data,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)
class Data