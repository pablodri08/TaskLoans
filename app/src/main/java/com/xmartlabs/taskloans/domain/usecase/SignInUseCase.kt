package com.xmartlabs.taskloans.domain.usecase

import com.xmartlabs.taskloans.data.model.User
import com.xmartlabs.taskloans.domain.repository.UserRepository
import com.xmartlabs.taskloans.domain.usecase.common.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by mirland on 25/04/20.
 */
class SignInUseCase(
    private val userRepository: UserRepository,
    dispatcher: CoroutineDispatcher
) : CoroutineUseCase<SignInUseCase.Params, User>(dispatcher) {
  data class Params(val id: String, val password: String)

  override suspend fun execute(params: Params): User = userRepository.signIn(params.id, params.password)
}
