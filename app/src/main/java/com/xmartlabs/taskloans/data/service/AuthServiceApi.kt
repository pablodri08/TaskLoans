package com.xmartlabs.taskloans.data.service

import com.xmartlabs.taskloans.data.model.User
import com.xmartlabs.taskloans.data.model.service.SignInRequest
import com.xmartlabs.taskloans.data.model.service.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServiceApi {
  companion object {
    private const val URL_SIGN_IN = "signin"
    private const val URL_SIGN_UP = "signup"
  }

  @POST(URL_SIGN_IN)
  suspend fun signInUser(@Body body: SignInRequest): AuthResponse

  @POST(URL_SIGN_UP)
  suspend fun signUpUser(@Body body: User): AuthResponse
}
