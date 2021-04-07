package com.xmartlabs.taskloans.data.service

import com.xmartlabs.taskloans.Config.URL_SIGN_IN
import com.xmartlabs.taskloans.Config.URL_SIGN_UP
import com.xmartlabs.taskloans.data.model.User
import com.xmartlabs.taskloans.data.model.service.AuthResponse
import com.xmartlabs.taskloans.data.model.service.SignInRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServiceApi {

  @POST(URL_SIGN_IN)
  suspend fun signInUser(@Body body: SignInRequest): AuthResponse

  @POST(URL_SIGN_UP)
  suspend fun signUpUser(@Body body: User): AuthResponse
}
