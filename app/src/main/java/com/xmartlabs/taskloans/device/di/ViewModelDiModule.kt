package com.xmartlabs.taskloans.device.di

import com.xmartlabs.taskloans.ui.screens.signin.SignInFragmentViewModel
import com.xmartlabs.taskloans.ui.screens.splash.SplashFragmentViewModel
import com.xmartlabs.taskloans.ui.screens.welcome.WelcomeFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by mirland on 25/04/20.
 */
object ViewModelDiModule {
  val viewModels = module {
    viewModel { SignInFragmentViewModel(get(), get()) }
    viewModel { SplashFragmentViewModel(get()) }
    viewModel { WelcomeFragmentViewModel(get(), get()) }
  }
}
