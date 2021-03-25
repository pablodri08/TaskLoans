package com.xmartlabs.taskloans.data.repository.auth

import com.xmartlabs.taskloans.data.common.DataHelper
import com.xmartlabs.taskloans.data.common.DataHelper.serviceErrorMapper
import com.xmartlabs.taskloans.data.model.User
import com.xmartlabs.taskloans.data.model.service.SignInRequest
import com.xmartlabs.taskloans.data.service.AuthServiceApi

/**
 * Created by mirland on 25/04/20.
 */
class UserRemoteSource(private val authServiceApi: AuthServiceApi) {

  suspend fun signIn(id: String, password: String) = DataHelper.mapServiceError(::serviceErrorMapper) {
    authServiceApi.signInUser(SignInRequest(id, password))
  }

  suspend fun signUp(email: String, password: String, name: String) = DataHelper.mapServiceError(::serviceErrorMapper) {
    authServiceApi.signUpUser(User(email, password, name))
  }
}
