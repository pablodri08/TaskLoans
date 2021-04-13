package com.xmartlabs.taskloans.data.repository.auth

import com.xmartlabs.taskloans.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by mirland on 25/04/20.
 */
class UserLocalSource {
  private val localUsers: MutableMap<String, User> = mutableMapOf()

  suspend fun createUser(user: User): User = withContext(Dispatchers.IO) {
    localUsers[user.id] = user
    user
  }

  suspend fun getUser(userId: String): User {
    return requireNotNull(localUsers[userId])
  }
}
