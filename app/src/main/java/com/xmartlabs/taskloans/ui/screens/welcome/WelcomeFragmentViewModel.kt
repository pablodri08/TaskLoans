package com.xmartlabs.taskloans.ui.screens.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xmartlabs.taskloans.data.model.Location
import com.xmartlabs.taskloans.data.model.User
import com.xmartlabs.taskloans.device.common.ProcessState
import com.xmartlabs.taskloans.domain.usecase.GetLocationUseCase
import com.xmartlabs.taskloans.domain.usecase.LoadUserUseCase

/**
 * Created by mirland on 25/04/20.
 */
class WelcomeFragmentViewModel(
    getLocationUseCase: GetLocationUseCase,
    loadUserUseCase: LoadUserUseCase
) : ViewModel() {
  val userLiveData: LiveData<ProcessState<User?>> = loadUserUseCase.invokeAsLiveData(Unit)

  val locationLiveData: LiveData<Result<Location>> = getLocationUseCase.invokeAsLiveData(Unit)
}
