package com.xmartlabs.taskloans.data.repository.task

import com.xmartlabs.taskloans.data.repository.RemoteSource
import com.xmartlabs.taskloans.data.service.TaskServiceApi

class TaskRemoteSource(private val taskServiceApi: TaskServiceApi) : RemoteSource() {

  suspend fun getTaskUsers() = invokeServiceCall { taskServiceApi.getTaskUsers() }
}
