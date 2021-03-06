package com.xmartlabs.taskloans

import com.xmartlabs.taskloans.device.di.DiAppModules
import com.xmartlabs.taskloans.device.logger.NoOpsNavigationLogger
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : AppBase() {
  companion object {
    lateinit var instance: App
      private set
  }

  override fun setupKoinModules() {
    startKoin {
      androidContext(this@App)
      modules(DiAppModules.provideModules(NoOpsNavigationLogger()))
    }
  }

  override fun onCreate() {
    instance = this
    super.onCreate()
  }
}
