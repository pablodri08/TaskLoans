package com.xmartlabs.taskloans.domain.usecase

import com.xmartlabs.taskloans.domain.repository.SessionRepository
import com.xmartlabs.taskloans.domain.usecase.common.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by mirland on 03/05/20.
 */
class GetSessionTypeUseCase(
    private val sessionRepository: SessionRepository,
    dispatcher: CoroutineDispatcher,
) : CoroutineUseCase<Unit, SessionType>(dispatcher) {
  override suspend fun execute(params: Unit): SessionType =
      sessionRepository.isUserLogged()
          .let { isUserLogged -> if (isUserLogged) SessionType.LOGGED else SessionType.NOT_LOGGED }
}

enum class SessionType {
  LOGGED,
  NOT_LOGGED
}
