package com.xmartlabs.taskloans.domain.usecase

import com.xmartlabs.taskloans.data.model.service.BalanceResponse
import com.xmartlabs.taskloans.domain.repository.TaskRepository
import com.xmartlabs.taskloans.domain.usecase.common.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher

class TaskBalanceUseCase(
    private val taskRepository: TaskRepository,
    dispatcher: CoroutineDispatcher,
) : CoroutineUseCase<Unit, List<BalanceResponse>>(dispatcher) {

  override suspend fun execute(params: Unit): List<BalanceResponse> = taskRepository.getTaskBalance()
}
