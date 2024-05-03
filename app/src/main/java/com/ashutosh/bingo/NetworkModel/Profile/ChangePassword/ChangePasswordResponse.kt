package com.ashutosh.bingo.NetworkModel.Profile.ChangePassword

data class ChangePasswordResponse(
    val `data`: Data,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)

class Data