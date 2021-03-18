package com.xmartlabs.taskloans.data.repository.auth

import android.util.Log
import com.xmartlabs.taskloans.data.model.service.SignInRequest
import com.xmartlabs.taskloans.data.model.service.SignInResponse
import com.xmartlabs.taskloans.data.service.OutServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

/**
 * Created by mirland on 25/04/20.
 */
class UserRemoteSource(private val outServiceApi: OutServiceApi) {

  suspend fun signIn(id: String, password: String) = outServiceApi.signInUser(SignInRequest(id, password))
}
