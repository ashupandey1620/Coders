package com.ashutosh.bingo.ApiInterface

import com.ashutosh.bingo.NetworkModel.Profile.ChangePassword.ChangePasswordRequest
import com.ashutosh.bingo.NetworkModel.Profile.ChangePassword.ChangePasswordResponse
import com.ashutosh.bingo.NetworkModel.Profile.Login.LoginRequest
import com.ashutosh.bingo.NetworkModel.Profile.Login.LoginResponse
import com.ashutosh.bingo.NetworkModel.Profile.Logout.LogoutResponse
import com.ashutosh.bingo.NetworkModel.Profile.ProfileData.ProfileResponse
import com.ashutosh.bingo.NetworkModel.Profile.RefreshToken.RefreshTokenRequest
import com.ashutosh.bingo.NetworkModel.Profile.RefreshToken.RefreshTokenResponse
import com.ashutosh.bingo.NetworkModel.Profile.UpdateAccount.UpdateAccountRequest
import com.ashutosh.bingo.NetworkModel.Profile.UpdateAccount.UpdateAccountResponse
import com.ashutosh.bingo.NetworkModel.Task.AddTask.AddTaskRequest
import com.ashutosh.bingo.NetworkModel.Task.AddTask.AddTaskResponse
import com.ashutosh.bingo.NetworkModel.Task.DeleteAllTask.DeleteAllTask
import com.ashutosh.bingo.NetworkModel.Task.DeleteAllTask.DeleteAllTaskResponse
import com.ashutosh.bingo.NetworkModel.Task.DeleteTask.DeleteTask
import com.ashutosh.bingo.NetworkModel.Task.DeleteTask.DeleteTaskResponse
import com.ashutosh.bingo.NetworkModel.Task.UpdateTask.UpdateTaskRequest
import com.ashutosh.bingo.NetworkModel.Task.UpdateTask.UpdateTaskResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap

interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("/users/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("/users/logout")
    suspend fun logout(): Response<LogoutResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/user/register/")
    suspend fun refreshToken(
        @Body refreshToken: RefreshTokenRequest
    ):Response<RefreshTokenResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/user/updateAccount/")
    suspend fun updateAccount(
        @Body updateAccountRequest: UpdateAccountRequest
    ):Response<UpdateAccountResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/user/changePassword/")
    suspend fun changePassword(
        @Body changePasswordRequest: ChangePasswordRequest
    ):Response<ChangePasswordResponse>


    @Headers("Content-Type: application/json")
    @GET("/api/user/changePassword/")
    suspend fun profile():Response<ProfileResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/user/addTask/")
    suspend fun addTask(
        @Body addTaskRequest: AddTaskRequest
    ):Response<AddTaskResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/user/updateTask/")
    suspend fun updateTask(
        @Body updateTaskRequest: UpdateTaskRequest
    ):Response<UpdateTaskResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/user/deleteTask/")
    suspend fun deleteTask(
        @Body deleteTask: DeleteTask
    ):Response<DeleteTaskResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/user/deleteAllTask/")
    suspend fun deleteAllTask(
        @Body deleteAllTask: DeleteAllTask
    ):Response<DeleteAllTaskResponse>



}