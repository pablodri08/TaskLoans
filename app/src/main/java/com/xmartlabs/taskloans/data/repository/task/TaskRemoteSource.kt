package com.xmartlabs.taskloans.data.repository.task

import com.xmartlabs.taskloans.Config.PAGE_SIZE
import com.xmartlabs.taskloans.data.repository.RemoteSource
import com.xmartlabs.taskloans.data.service.TaskServiceApi

class TaskRemoteSource(private val taskServiceApi: TaskServiceApi) : RemoteSource() {

  suspend fun getTaskUsers() = invokeServiceCall { taskServiceApi.getTaskUsers() }

  suspend fun getTaskEntries(userId: String, page: Int) = invokeServiceCall {
    taskServiceApi.getTaskEntries(userId, page, PAGE_SIZE)
  }

  suspend fun getTaskBalance() = invokeServiceCall {
    taskServiceApi.getTaskBalance()
  }
}
