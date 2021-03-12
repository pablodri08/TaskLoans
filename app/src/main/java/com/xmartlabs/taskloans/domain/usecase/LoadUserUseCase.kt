package com.xmartlabs.taskloans.domain.usecase

import com.xmartlabs.taskloans.data.model.User
import com.xmartlabs.taskloans.domain.repository.UserRepository
import com.xmartlabs.taskloans.domain.usecase.common.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first

/**
 * Created by mirland on 25/04/20.
 */
class LoadUserUseCase(
    private val userRepository: UserRepository,
    dispatcher: CoroutineDispatcher
) : CoroutineUseCase<Unit, User?>(dispatcher) {
  override suspend fun execute(params: Unit): User? =
      userRepository.getCurrentUser()
          .first()
}
