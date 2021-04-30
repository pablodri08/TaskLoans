package com.xmartlabs.taskloans.data.service

import com.xmartlabs.taskloans.Config.CLEAN_DISHES_TASK_ID
import com.xmartlabs.taskloans.data.model.service.BalanceResponse
import com.xmartlabs.taskloans.data.model.service.PagingResponse
import com.xmartlabs.taskloans.data.model.service.PagingResponseData
import com.xmartlabs.taskloans.data.model.service.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TaskServiceApi {
  companion object {
    const val URL_TASKS_USERS = "tasks/$CLEAN_DISHES_TASK_ID/users"
    const val URL_TASKS_ENTRIES = "$URL_TASKS_USERS/{user_id}/entries"
    const val URL_TASKS_BALANCE = "tasks/$CLEAN_DISHES_TASK_ID/balance"
  }

  @GET(URL_TASKS_USERS)
  suspend fun getTaskUsers(): List<UserResponse>

  @GET(URL_TASKS_ENTRIES)
  suspend fun getTaskEntries(
      @Path("user_id") userId: String,
      @Query("page") page: Int,
      @Query("per_page") perPage: Int,
  ): PagingResponse<PagingResponseData>

  @GET(URL_TASKS_BALANCE)
  suspend fun getTaskBalance(): List<BalanceResponse>
}
