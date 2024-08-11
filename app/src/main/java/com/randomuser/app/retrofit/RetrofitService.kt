package com.randomuser.app.retrofit

import com.randomuser.app.data.Results
import com.randomuser.app.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET(Constants.GET_USERS_URL)
    suspend fun getAllUsers() : Response<Results>
}