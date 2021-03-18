package com.xmartlabs.taskloans.data.service

import com.xmartlabs.taskloans.data.model.User
import com.xmartlabs.taskloans.data.model.service.SignInRequest
import com.xmartlabs.taskloans.data.model.service.SignInResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface OutServiceApi {
  companion object {
    private const val URL_SIGN_IN = "signin"
  }

  @POST(URL_SIGN_IN)
  suspend fun signInUser(@Body body: SignInRequest): SignInResponse
}
