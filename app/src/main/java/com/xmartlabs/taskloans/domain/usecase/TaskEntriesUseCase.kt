package com.xmartlabs.taskloans.domain.usecase

import com.xmartlabs.taskloans.data.model.UserIdEntries
import com.xmartlabs.taskloans.domain.repository.TaskRepository
import com.xmartlabs.taskloans.domain.usecase.common.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher

class TaskEntriesUseCase(
    private val taskRepository: TaskRepository,
    dispatcher: CoroutineDispatcher,
) : CoroutineUseCase<Unit, UserIdEntries>(dispatcher) {

  override suspend fun execute(params: Unit): UserIdEntries = taskRepository.getTaskEntries()
}
