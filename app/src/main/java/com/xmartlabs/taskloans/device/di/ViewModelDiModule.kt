package com.xmartlabs.taskloans.device.di

import com.xmartlabs.taskloans.ui.screens.dashboard.DashboardFragmentViewModel
import com.xmartlabs.taskloans.ui.screens.signin.SignInFragmentViewModel
import com.xmartlabs.taskloans.ui.screens.signup.SignUpFragmentViewModel
import com.xmartlabs.taskloans.ui.screens.splash.SplashFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by mirland on 25/04/20.
 */
object ViewModelDiModule {
  val viewModels = module {
    viewModel { SignInFragmentViewModel(get()) }
    viewModel { SplashFragmentViewModel(get()) }
    viewModel { DashboardFragmentViewModel(get(), get()) }
    viewModel { SignUpFragmentViewModel(get()) }
  }
}
