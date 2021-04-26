package com.xmartlabs.taskloans.data.repository.auth

import com.xmartlabs.taskloans.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by mirland on 25/04/20.
 */
class UserLocalSource {
  companion object {
    const val DUMMYDATA = ""
  }

  suspend fun createUser(user: User): User = withContext(Dispatchers.IO) {
    //TODO: Use datastore
    user
  }

  suspend fun getUser(userId: String): User {
    //TODO: Use datastore
    return User(id = userId, DUMMYDATA, DUMMYDATA, emptyList()) //DUMMY USER
  }
}
