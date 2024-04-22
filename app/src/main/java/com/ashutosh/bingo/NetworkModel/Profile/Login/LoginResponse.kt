package com.ashutosh.bingo.NetworkModel.Profile.Login

data class LoginResponse(
    val `data`: Data ,
    val message: String ,
    val statusCode: Int ,
    val success: Boolean
)

data class Data(
    val accessToken: String,
    val refreshToken: String,
    val user: User
)

data class User(
    val __v: Int,
    val _id: String,
    val avatar: String,
    val coverImage: String,
    val createdAt: String,
    val email: String,
    val fullname: String,
    val updatedAt: String,
    val username: String,
    val watchHistory: List<Any>
)