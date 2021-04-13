package com.xmartlabs.taskloans.domain.repository

import com.xmartlabs.taskloans.data.model.service.UserResponse
import com.xmartlabs.taskloans.data.repository.task.TaskRemoteSource

class TaskRepository(
    private val taskRemoteSource: TaskRemoteSource
) {

  suspend fun getTaskUsers(): List<UserResponse> = taskRemoteSource.getTaskUsers()
}
