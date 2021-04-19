package com.xmartlabs.taskloans.domain.repository

import com.xmartlabs.taskloans.data.model.service.EntriesResponse
import com.xmartlabs.taskloans.data.model.service.UserResponse
import com.xmartlabs.taskloans.data.repository.session.SessionLocalSource
import com.xmartlabs.taskloans.data.repository.task.TaskRemoteSource

class TaskRepository(
    private val taskRemoteSource: TaskRemoteSource,
    private val sessionLocalSource: SessionLocalSource,
) {

  suspend fun getUserEntries(): List<UserResponse> = taskRemoteSource.getTaskUsers()

  suspend fun getTaskEntries(userId: String, page: Int): EntriesResponse = taskRemoteSource.getTaskEntries(userId, page)
}
