package com.ashutosh.bingo.NetworkModel.Profile.ProfileData

data class ProfileResponse(
    val `data`: Data,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)