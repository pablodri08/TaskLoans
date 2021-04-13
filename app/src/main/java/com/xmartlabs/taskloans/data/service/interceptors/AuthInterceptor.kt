package com.xmartlabs.taskloans.data.service.interceptors

import com.xmartlabs.taskloans.data.repository.session.SessionLocalSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AuthInterceptor(private val sessionLocalSource: SessionLocalSource) : Interceptor {

  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    var request: Request = chain.request()
    val builder: Request.Builder = request.newBuilder() // build new request
    var token: String?
    runBlocking {
      token = sessionLocalSource.getSessionToken()
    }
    setAuthHeader(builder, token)
    request = builder.build()
    return chain.proceed(request)
  }

  private fun setAuthHeader(builder: Request.Builder, token: String?) {
    if (token != null) {
      builder.header("Authorization", String.format("Bearer %s", token))
    }
  }
}
