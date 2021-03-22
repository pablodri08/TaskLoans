package com.xmartlabs.taskloans.data.repository.auth

import com.xmartlabs.taskloans.data.model.service.SignInRequest
import com.xmartlabs.taskloans.data.service.AuthServiceApi

/**
 * Created by mirland on 25/04/20.
 */
class UserRemoteSource(private val outServiceApi: AuthServiceApi) {

  suspend fun signIn(id: String, password: String) = outServiceApi.signInUser(SignInRequest(id, password))
}
