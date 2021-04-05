package com.xmartlabs.taskloans.data.service

import com.xmartlabs.taskloans.data.model.service.UserResponse
import retrofit2.http.GET

interface TaskServiceApi {
  companion object {
    private const val URL_TASKS_USERS = "tasks/1/users"
  }

  @GET(URL_TASKS_USERS)
  suspend fun getTaskUsers(): List<UserResponse>
}
