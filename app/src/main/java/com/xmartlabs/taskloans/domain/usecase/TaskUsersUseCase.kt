package com.xmartlabs.taskloans.domain.usecase

import com.xmartlabs.taskloans.data.model.service.UserResponse
import com.xmartlabs.taskloans.domain.repository.TaskRepository
import com.xmartlabs.taskloans.domain.usecase.common.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher

class TaskUsersUseCase(
    private val taskRepository: TaskRepository,
    dispatcher: CoroutineDispatcher,
) : CoroutineUseCase<Unit, List<UserResponse>>(dispatcher) {

  override suspend fun execute(params: Unit): List<UserResponse> = taskRepository.getUserEntries()
}
