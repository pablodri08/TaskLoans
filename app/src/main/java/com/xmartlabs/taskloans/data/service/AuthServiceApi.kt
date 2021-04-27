package com.xmartlabs.taskloans.data.service

import com.xmartlabs.taskloans.data.model.service.AuthResponse
import com.xmartlabs.taskloans.data.model.service.SignInRequest
import com.xmartlabs.taskloans.data.model.service.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServiceApi {
  companion object {
    const val URL_SIGN_IN = "signin"
    const val URL_SIGN_UP = "signup"
  }

  @POST(URL_SIGN_IN)
  suspend fun signInUser(@Body body: SignInRequest): AuthResponse

  @POST(URL_SIGN_UP)
  suspend fun signUpUser(@Body body: SignUpRequest): AuthResponse
}
