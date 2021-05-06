package com.xmartlabs.taskloans.ui.screens.signup

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.xmartlabs.swissknife.core.extensions.disable
import com.xmartlabs.swissknife.core.extensions.enable
import com.xmartlabs.swissknife.core.extensions.gone
import com.xmartlabs.swissknife.core.extensions.visible
import com.xmartlabs.taskloans.R
import com.xmartlabs.taskloans.data.common.ServerException
import com.xmartlabs.taskloans.data.common.UserConflictException
import com.xmartlabs.taskloans.databinding.FragmentSignupBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment
import com.xmartlabs.taskloans.ui.common.UIHelper
import com.xmartlabs.taskloans.ui.common.extensions.observeStateResult
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : BaseViewBindingFragment<FragmentSignupBinding>() {
  private val viewModel: SignUpFragmentViewModel by viewModel()

  override fun inflateViewBinding(): FragmentSignupBinding =
      FragmentSignupBinding.inflate(layoutInflater)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupButtons()
    setupViewModelCallbacks()
  }

  @SuppressLint("SetTextI18n")
  private fun setupViewModelCallbacks() = with(viewModel) {
    signUp.observeStateResult(viewLifecycleOwner,
        onFailure = { throwable ->
          withViewBinding {
            signUpProgressBar.gone()
            signUpCreateButton.enable()
          }
          if (throwable is UserConflictException) displayError(getString(R.string.text_toast_error_user_conflict))
          else if (throwable is ServerException) displayError(getString(R.string.text_toast_error_server))
        },
        onSuccess = {
          withViewBinding { signUpProgressBar.gone() }
          router.navigate(
              SignUpFragmentDirections.actionSignUpFragmentToSignInFragment()
          )
        }
    )
  }

  private fun setupButtons() = withViewBinding {
    signUpCreateButton.setOnClickListener {
      val errorInputMessage = getString(R.string.text_toast_error_input)
      val isValidMail = UIHelper.validateMail(signUpMailEditText.text.toString())
      val isValidPassword = UIHelper.validatePassword(signUpPassEditText.text.toString())
      val isValidName = signUpUserEditText.text.toString().isNotEmpty()
      UIHelper.showTextFieldError(isValidMail, signUpMailTextField, errorInputMessage)
      UIHelper.showTextFieldError(isValidPassword, signUpPassTextField, errorInputMessage)
      UIHelper.showTextFieldError(isValidName, signUpUserTextField, errorInputMessage)
      if (isValidMail && isValidPassword && isValidName) {
        signUpCreateButton.disable()
        signUpProgressBar.visible()
        viewModel.signUp(
            signUpMailEditText.text.toString(),
            signUpPassEditText.text.toString(),
            signUpUserEditText.text.toString()
        )
      } else {
        displayError(getString(R.string.text_toast_error_input))
      }
    }
  }
}
