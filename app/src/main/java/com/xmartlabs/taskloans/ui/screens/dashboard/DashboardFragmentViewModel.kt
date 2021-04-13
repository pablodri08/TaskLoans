package com.xmartlabs.taskloans.ui.screens.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xmartlabs.taskloans.data.model.User
import com.xmartlabs.taskloans.data.model.UserIdEntries
import com.xmartlabs.taskloans.data.model.service.UserResponse
import com.xmartlabs.taskloans.device.common.ProcessState
import com.xmartlabs.taskloans.domain.usecase.LoadUserUseCase
import com.xmartlabs.taskloans.domain.usecase.TaskEntriesUseCase
import com.xmartlabs.taskloans.domain.usecase.TaskUsersUseCase

class DashboardFragmentViewModel(
    taskUsersUseCase: TaskUsersUseCase,
    loadUserUseCase: LoadUserUseCase,
    taskEntriesUseCase: TaskEntriesUseCase,
) : ViewModel() {
  val listUsersLiveData: LiveData<ProcessState<List<UserResponse>>> = taskUsersUseCase.invokeAsLiveData(Unit)
  val userLiveData: LiveData<ProcessState<User?>> = loadUserUseCase.invokeAsLiveData(Unit)
  val entriesLiveData: LiveData<ProcessState<UserIdEntries>> = taskEntriesUseCase.invokeAsLiveData(Unit)
}
