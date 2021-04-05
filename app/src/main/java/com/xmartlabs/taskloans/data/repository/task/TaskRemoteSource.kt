package com.xmartlabs.taskloans.data.repository.task

import com.xmartlabs.taskloans.data.common.DataHelper
import com.xmartlabs.taskloans.data.service.TaskServiceApi

class TaskRemoteSource(private val taskServiceApi: TaskServiceApi) {

  suspend fun getTaskUsers() = DataHelper.mapServiceError(DataHelper::serviceErrorMapper) {
    taskServiceApi.getTaskUsers()
  }
}
