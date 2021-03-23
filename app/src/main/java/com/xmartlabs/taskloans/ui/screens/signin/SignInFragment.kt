package com.xmartlabs.taskloans.ui.screens.signin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.xmartlabs.swissknife.core.extensions.disable
import com.xmartlabs.swissknife.core.extensions.enable
import com.xmartlabs.swissknife.core.extensions.gone
import com.xmartlabs.swissknife.core.extensions.visible
import com.xmartlabs.taskloans.R
import com.xmartlabs.taskloans.data.repository.common.InvalidUserException
import com.xmartlabs.taskloans.data.repository.common.ServerException
import com.xmartlabs.taskloans.databinding.FragmentSigninBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment
import com.xmartlabs.taskloans.ui.common.UIHelper
import com.xmartlabs.taskloans.ui.common.extensions.observeStateResult
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.time.ExperimentalTime

/**
 * Created by mirland on 25/04/20.
 */
class SignInFragment : BaseViewBindingFragment<FragmentSigninBinding>() {
  private val viewModel: SignInFragmentViewModel by viewModel()

  override fun inflateViewBinding(): FragmentSigninBinding =
      FragmentSigninBinding.inflate(layoutInflater)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupButtons()
    setupViewModelCallbacks()
  }

  @SuppressLint("SetTextI18n")
  @OptIn(ExperimentalTime::class)
  private fun setupViewModelCallbacks() = with(viewModel) {
    signIn.observeStateResult(viewLifecycleOwner,
        onFailure = { throwable ->
          withViewBinding {
            signInProgressBar.gone()
            signInEnterButton.enable()
          }
          if (throwable is InvalidUserException) {
             displayError(getString(R.string.text_toast_error_input))
          } else if (throwable is ServerException) {
            displayError(getString(R.string.text_toast_error_server))
          }
        },
        onSuccess = {
          withViewBinding { signInProgressBar.gone() }
          router.navigate(
              SignInFragmentDirections.actionSignInFragmentToWelcomeFragment()
          )
        }
    )
  }

  private fun setupButtons() = withViewBinding {
    signInEnterButton.setOnClickListener {
      val errorInputMessage = getString(R.string.text_toast_error_input)
      val isValidMail = UIHelper.validateMail(signInUserTextField.editText?.text.toString())
      val isValidPassword = UIHelper.validatePassword(signInPasswordEditText.text.toString())
      UIHelper.showTextFieldError(isValidMail, signInUserTextField, errorInputMessage)
      UIHelper.showTextFieldError(isValidPassword, signInPasswordTextField, errorInputMessage)
      if (isValidMail && isValidPassword) {
        signInEnterButton.disable()
        signInProgressBar.visible()
        viewModel.signIn(
            signInUserTextField.editText?.text.toString(),
            signInPasswordTextField.editText?.text.toString()
        )
      } else {
        Toast.makeText(
            requireContext(),
            getString(R.string.text_toast_error_input),
            Toast.LENGTH_SHORT
        ).show()
      }
    }
  }

  private fun displayError(error: String) = Toast.makeText(
      requireContext(),
      error,
      Toast.LENGTH_SHORT
  ).show()
}