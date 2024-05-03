package com.ashutosh.bingo.NetworkModel.Profile.ChangePassword

data class ChangePasswordRequest(
    val confirmPassword: String,
    val newPassword: String,
    val oldPassword: String
)