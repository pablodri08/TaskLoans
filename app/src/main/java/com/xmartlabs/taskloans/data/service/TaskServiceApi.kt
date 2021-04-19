package com.xmartlabs.taskloans.data.service

import com.xmartlabs.taskloans.Config.TASK_LOANS_ID
import com.xmartlabs.taskloans.data.model.service.EntriesResponse
import com.xmartlabs.taskloans.data.model.service.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TaskServiceApi {
  companion object {
    const val URL_TASKS_USERS = "tasks/$TASK_LOANS_ID/users"
    const val URL_TASKS_ENTRIES = "$URL_TASKS_USERS/{user_id}/entries"
  }

  @GET(URL_TASKS_USERS)
  suspend fun getTaskUsers(): List<UserResponse>

  @GET(URL_TASKS_ENTRIES)
  suspend fun getTaskEntries(
      @Path("user_id") userId: String,
      @Query("page") page: Int,
      @Query("per_page") perPage: Int,
  ): EntriesResponse
}
