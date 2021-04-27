package com.xmartlabs.taskloans.domain.repository

import com.xmartlabs.taskloans.data.model.service.PagingResponse
import com.xmartlabs.taskloans.data.model.service.PagingResponseData
import com.xmartlabs.taskloans.data.model.service.UserResponse
import com.xmartlabs.taskloans.data.repository.task.TaskRemoteSource

class TaskRepository(
    private val taskRemoteSource: TaskRemoteSource,
) {

  suspend fun getUserEntries(): List<UserResponse> = taskRemoteSource.getTaskUsers()

  suspend fun getTaskEntries(userId: String, page: Int): PagingResponse<PagingResponseData> =
      taskRemoteSource.getTaskEntries(userId, page)
}
