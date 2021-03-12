package com.xmartlabs.taskloans.device.di

import com.xmartlabs.taskloans.device.logger.NavigationLogger
import org.koin.dsl.module

/**
 * Created by mirland on 25/05/20.
 */
object DiAppModules {
  private val modules = listOf(
      DispatchersDiModule.dispatchers,
      NetworkDiModule.network,
      RepositoryDiModuleProvider.repositories,
      RepositoryDiModuleProvider.sources,
      RepositoryDiModuleProvider.stores,
      UseCaseDiModule.useCases,
      ViewModelDiModule.viewModels
  )

  fun provideModules(navigationLogger: NavigationLogger) =
      modules + module { single { navigationLogger } }
}
