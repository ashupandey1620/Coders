package com.ashutosh.bingo.NetworkModel.Profile.RefreshToken

data class RefreshTokenResponse(
    val `data`: Data,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)

data class Data(
    val accessToken: String
)