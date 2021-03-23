package com.xmartlabs.taskloans.domain.usecase

import com.xmartlabs.taskloans.data.model.User
import com.xmartlabs.taskloans.domain.repository.UserRepository
import com.xmartlabs.taskloans.domain.usecase.common.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher

class SignUpUseCase(
  private val userRepository: UserRepository,
  dispatcher: CoroutineDispatcher
  ): CoroutineUseCase<SignUpUseCase.Params, User>(dispatcher) {
    data class Params(val email: String, val name: String, val password: String)

    override suspend fun execute(params: Params): User = userRepository.signUp(params.email, params.password, params.name)
}