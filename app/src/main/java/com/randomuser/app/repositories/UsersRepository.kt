package com.randomuser.app.repositories

import com.randomuser.app.data.Results
import com.randomuser.app.network.UsersNetworkDataSource
import retrofit2.Response
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersNetworkDataSource: UsersNetworkDataSource
) {
    suspend fun getAllUsers(): Response<Results> = usersNetworkDataSource.getAllUsers()
}