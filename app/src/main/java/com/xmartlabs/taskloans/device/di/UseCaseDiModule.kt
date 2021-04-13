package com.xmartlabs.taskloans.device.di

import com.xmartlabs.taskloans.domain.usecase.GetLocationUseCase
import com.xmartlabs.taskloans.domain.usecase.GetSessionTypeUseCase
import com.xmartlabs.taskloans.domain.usecase.LoadUserUseCase
import com.xmartlabs.taskloans.domain.usecase.SignInUseCase
import com.xmartlabs.taskloans.domain.usecase.SignUpUseCase
import com.xmartlabs.taskloans.domain.usecase.TaskEntriesUseCase
import com.xmartlabs.taskloans.domain.usecase.TaskUsersUseCase
import com.xmartlabs.taskloans.domain.usecase.TimeTrackerUseCase
import org.koin.dsl.module

/**
 * Created by mirland on 25/04/20.
 */
object UseCaseDiModule {
  val useCases = module {
    factory { GetLocationUseCase(get(), get(DEFAULT_DISPATCHER)) }
    factory { GetSessionTypeUseCase(get(), get(DEFAULT_DISPATCHER)) }
    factory { LoadUserUseCase(get(), get(DEFAULT_DISPATCHER)) }
    factory { SignInUseCase(get(), get(DEFAULT_DISPATCHER)) }
    factory { TimeTrackerUseCase(get(DEFAULT_DISPATCHER)) }
    factory { SignUpUseCase(get(), get(DEFAULT_DISPATCHER)) }
    factory { TaskUsersUseCase(get(), get(DEFAULT_DISPATCHER)) }
    factory { TaskEntriesUseCase(get(), get(DEFAULT_DISPATCHER)) }
  }
}
