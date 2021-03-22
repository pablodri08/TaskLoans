package com.xmartlabs.taskloans.ui.screens.signup

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.xmartlabs.taskloans.data.model.User
import com.xmartlabs.taskloans.device.common.ProcessState
import com.xmartlabs.taskloans.domain.usecase.SignInUseCase

class SignUpFragmentViewModel(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
  private val signUpMutableLiveData = MutableLiveData<SignUpUseCase.Params>()

  val signUp: LiveData<ProcessState<User>> = signUpMutableLiveData
      .switchMap { params -> SignUpUseCase.invokeAsLiveData(params) }

  @MainThread
  fun signUp(userId: String, mail: String, password: String) {
    signUpMutableLiveData.value = SignUpUseCase.Params(userId, mail, password)
  }
}
