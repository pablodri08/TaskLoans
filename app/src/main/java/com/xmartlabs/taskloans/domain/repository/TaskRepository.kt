package com.xmartlabs.taskloans.domain.repository

import com.xmartlabs.taskloans.data.model.UserIdEntries
import com.xmartlabs.taskloans.data.model.service.UserResponse
import com.xmartlabs.taskloans.data.repository.session.SessionLocalSource
import com.xmartlabs.taskloans.data.repository.task.TaskRemoteSource
import kotlinx.coroutines.flow.first

class TaskRepository(
    private val taskRemoteSource: TaskRemoteSource,
    private val sessionLocalSource: SessionLocalSource,
) {
  suspend fun getUserEntries(): List<UserResponse> = taskRemoteSource.getTaskUsers()

  suspend fun getTaskEntries(): UserIdEntries {
    val user = sessionLocalSource.getSessionUser().first()!!
    return UserIdEntries(taskRemoteSource.getTaskEntries(user.id).data, user.id)
  }
}
