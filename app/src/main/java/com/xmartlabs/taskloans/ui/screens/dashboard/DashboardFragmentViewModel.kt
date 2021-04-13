package com.xmartlabs.taskloans.ui.screens.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xmartlabs.taskloans.data.model.User
import com.xmartlabs.taskloans.data.model.service.UserResponse
import com.xmartlabs.taskloans.device.common.ProcessState
import com.xmartlabs.taskloans.domain.usecase.LoadUserUseCase
import com.xmartlabs.taskloans.domain.usecase.TaskUseCase

class DashboardFragmentViewModel(
    taskUseCase: TaskUseCase,
    loadUserUseCase: LoadUserUseCase
) : ViewModel() {
  val listUsersLiveData: LiveData<ProcessState<List<UserResponse>>> = taskUseCase.invokeAsLiveData(Unit)
  val userLiveData: LiveData<ProcessState<User?>> = loadUserUseCase.invokeAsLiveData(Unit)
}
