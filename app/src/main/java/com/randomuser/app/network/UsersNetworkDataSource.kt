package com.randomuser.app.network

import com.randomuser.app.retrofit.RetrofitService
import javax.inject.Inject

class UsersNetworkDataSource @Inject constructor (
    private val retrofitService: RetrofitService
) {
    suspend fun getAllUsers() = retrofitService.getAllUsers()
}