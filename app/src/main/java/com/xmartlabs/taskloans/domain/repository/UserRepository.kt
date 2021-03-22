package com.xmartlabs.taskloans.domain.repository

import com.xmartlabs.taskloans.data.model.User
import com.xmartlabs.taskloans.data.repository.auth.UserLocalSource
import com.xmartlabs.taskloans.data.repository.auth.UserRemoteSource
import com.xmartlabs.taskloans.data.repository.session.SessionLocalSource

/**
 * Created by mirland on 25/04/20.
 */
class UserRepository(
    private val userLocalSource: UserLocalSource,
    private val userRemoteSource: UserRemoteSource,
    private val sessionLocalSource: SessionLocalSource
) {

  suspend fun signIn(id: String, password: String) =
      userRemoteSource.signIn(id, password)
          .also { (token: String?, user: User?) ->
              userLocalSource.createUser(user!!)
              sessionLocalSource.setSession(user, token!!)
          }.user!!

  suspend fun getCurrentUser() = sessionLocalSource.getSessionUser()

  suspend fun getUser(userId: String) = userLocalSource.getUser(userId)
}
