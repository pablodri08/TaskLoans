package com.xmartlabs.taskloans.ui.screens.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.xmartlabs.taskloans.Config.PAGE_SIZE
import com.xmartlabs.taskloans.data.model.User
import com.xmartlabs.taskloans.data.model.UserEntry
import com.xmartlabs.taskloans.data.model.service.BalanceResponse
import com.xmartlabs.taskloans.data.model.service.UserResponse
import com.xmartlabs.taskloans.device.common.ProcessState
import com.xmartlabs.taskloans.domain.paging.TaskRemotePagingSource
import com.xmartlabs.taskloans.domain.repository.SessionRepository
import com.xmartlabs.taskloans.domain.repository.TaskRepository
import com.xmartlabs.taskloans.domain.usecase.LoadUserUseCase
import com.xmartlabs.taskloans.domain.usecase.TaskBalanceUseCase
import com.xmartlabs.taskloans.domain.usecase.TaskUsersUseCase
import kotlinx.coroutines.flow.Flow

class DashboardFragmentViewModel(
    loadUserUseCase: LoadUserUseCase,
    sessionRepository: SessionRepository,
    taskBalanceUseCase: TaskBalanceUseCase,
    taskRepository: TaskRepository,
    taskUsersUseCase: TaskUsersUseCase,
) : ViewModel() {
  val listUsersLiveData: LiveData<ProcessState<List<UserResponse>>> = taskUsersUseCase.invokeAsLiveData(Unit)
  val userLiveData: LiveData<ProcessState<User?>> = loadUserUseCase.invokeAsLiveData(Unit)
  val balanceLiveData: LiveData<ProcessState<List<BalanceResponse>>> = taskBalanceUseCase.invokeAsLiveData(Unit)
  val entries: Flow<PagingData<UserEntry>> = Pager(
      PagingConfig(pageSize = PAGE_SIZE)
  ) {
    TaskRemotePagingSource(taskRepository, sessionRepository)
  }
      .flow.cachedIn(viewModelScope)
}
