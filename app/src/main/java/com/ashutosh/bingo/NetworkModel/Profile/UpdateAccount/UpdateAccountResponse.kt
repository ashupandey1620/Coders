package com.ashutosh.bingo.NetworkModel.Profile.UpdateAccount

data class UpdateAccountResponse(
    val `data`: Data,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)

data class Data(
    val __v: Int,
    val _id: String,
    val avatar: String,
    val coverImage: String,
    val createdAt: String,
    val email: String,
    val fullname: String,
    val refreshToken: String,
    val updatedAt: String,
    val username: String,
    val watchHistory: List<Any>
)