package com.xmartlabs.taskloans.domain.repository

import com.xmartlabs.taskloans.data.repository.session.SessionLocalSource
import kotlinx.coroutines.flow.first

/**
 * Created by mirland on 03/05/20.
 */
class SessionRepository(private val sessionLocalSource: SessionLocalSource) {
  private suspend fun getSessionToken() = sessionLocalSource.getSessionToken()
  suspend fun isUserLogged() = !getSessionToken().isNullOrBlank()
  suspend fun getSessionUser() = sessionLocalSource.getSessionUser().first()!!
}
