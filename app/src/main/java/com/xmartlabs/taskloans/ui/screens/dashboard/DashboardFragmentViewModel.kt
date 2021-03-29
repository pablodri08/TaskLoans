package com.xmartlabs.taskloans.ui.screens.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xmartlabs.taskloans.data.model.User
import com.xmartlabs.taskloans.device.common.ProcessState
import com.xmartlabs.taskloans.domain.usecase.LoadUserUseCase

class DashboardFragmentViewModel(
    loadUserUseCase: LoadUserUseCase
) : ViewModel() {
  val userLiveData: LiveData<ProcessState<User?>> = loadUserUseCase.invokeAsLiveData(Unit)
}
