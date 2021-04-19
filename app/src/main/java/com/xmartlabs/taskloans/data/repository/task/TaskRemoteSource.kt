package com.xmartlabs.taskloans.data.repository.task

import com.xmartlabs.taskloans.data.repository.RemoteSource
import com.xmartlabs.taskloans.data.service.TaskServiceApi
import com.xmartlabs.taskloans.domain.paging.TaskRemotePagingSource

class TaskRemoteSource(private val taskServiceApi: TaskServiceApi) : RemoteSource() {

  suspend fun getTaskUsers() = invokeServiceCall { taskServiceApi.getTaskUsers() }

  suspend fun getTaskEntries(userId: String, page: Int) = invokeServiceCall {
    taskServiceApi.getTaskEntries(userId, page, TaskRemotePagingSource.PAGE_SIZE)
  }
}
