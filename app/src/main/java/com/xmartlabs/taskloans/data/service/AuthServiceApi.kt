package com.xmartlabs.taskloans.data.service

import com.xmartlabs.taskloans.data.model.service.SignInRequest
import com.xmartlabs.taskloans.data.model.service.SignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServiceApi {
  companion object {
    private const val URL_SIGN_IN = "signin"
  }

  @POST(URL_SIGN_IN)
  suspend fun signInUser(@Body body: SignInRequest): SignInResponse
}
