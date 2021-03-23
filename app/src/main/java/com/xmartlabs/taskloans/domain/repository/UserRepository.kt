package com.xmartlabs.taskloans.domain.repository

import com.xmartlabs.taskloans.data.model.User
import com.xmartlabs.taskloans.data.repository.auth.UserLocalSource
import com.xmartlabs.taskloans.data.repository.auth.UserRemoteSource
import com.xmartlabs.taskloans.data.repository.common.InvalidUserException
import com.xmartlabs.taskloans.data.repository.common.ServerException
import com.xmartlabs.taskloans.data.repository.common.UserConflictException
import com.xmartlabs.taskloans.data.repository.session.SessionLocalSource
import retrofit2.HttpException
import java.lang.Exception

/**
 * Created by mirland on 25/04/20.
 */
class UserRepository(
    private val userLocalSource: UserLocalSource,
    private val userRemoteSource: UserRemoteSource,
    private val sessionLocalSource: SessionLocalSource
) {

  suspend fun signIn(id: String, password: String): User {
    val response = userRemoteSource.signIn(id, password)
    try {
      userLocalSource.createUser(response.user!!)
      sessionLocalSource.setSession(response.user, response.token!!)
    } catch (e: Exception) {
      if (e is HttpException) {
        when (e.code()) {
          401 -> throw InvalidUserException(e.message())
          else -> throw ServerException(e.message())
        }
      }
    }
    return response.user!!
  }

  suspend fun signUp (email: String, password: String, name: String): User {
    val response = userRemoteSource.signUp(email, password, name)
    try {
      userLocalSource.createUser(response.user!!)
    } catch (e: Exception) {
      if (e is HttpException) {
        when (e.code()) {
          401 -> throw InvalidUserException(e.message())
          409 -> throw UserConflictException(e.message())
          else -> throw ServerException(e.message())
        }
      }
    }
    return response.user!!
  }

  suspend fun getCurrentUser() = sessionLocalSource.getSessionUser()

  suspend fun getUser(userId: String) = userLocalSource.getUser(userId)
}
