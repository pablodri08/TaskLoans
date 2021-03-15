package com.xmartlabs.taskloans.ui.screens.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xmartlabs.taskloans.device.common.ProcessState
import com.xmartlabs.taskloans.domain.usecase.GetSessionTypeUseCase
import com.xmartlabs.taskloans.domain.usecase.SessionType

/**
 * Created by mirland on 03/05/20.
 */
class SplashFragmentViewModel(getSessionTypeUseCase: GetSessionTypeUseCase) : ViewModel() {
    val currentSessionTypeLiveData: LiveData<ProcessState<SessionType>> =
            getSessionTypeUseCase.invokeAsLiveData(Unit)
}
