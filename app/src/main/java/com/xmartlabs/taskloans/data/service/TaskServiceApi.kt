package com.xmartlabs.taskloans.data.service

import com.xmartlabs.taskloans.Config.URL_TASKS_USERS
import com.xmartlabs.taskloans.data.model.service.UserResponse
import retrofit2.http.GET

interface TaskServiceApi {

  @GET(URL_TASKS_USERS)
  suspend fun getTaskUsers(): List<UserResponse>
}
