package com.xmartlabs.taskloans.ui.screens.signup

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.xmartlabs.swissknife.core.extensions.disable
import com.xmartlabs.swissknife.core.extensions.enable
import com.xmartlabs.swissknife.core.extensions.gone
import com.xmartlabs.swissknife.core.extensions.visible
import com.xmartlabs.taskloans.databinding.FragmentSigninBinding
import com.xmartlabs.taskloans.databinding.FragmentSignupBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment
import com.xmartlabs.taskloans.ui.common.extensions.observeStateResult
import com.xmartlabs.taskloans.ui.screens.signin.SignInFragmentDirections
import com.xmartlabs.taskloans.ui.screens.signin.SignInFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.time.ExperimentalTime


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
          Toast.makeText(
              requireContext(),
              "Invalid mail format!",
              Toast.LENGTH_SHORT
          ).show()
        },
        onSuccess = {
          withViewBinding { signUpProgressBar.gone() }
          router.navigate(
              SignInFragmentDirections.actionSignInFragmentToWelcomeFragment()
          )
        }
    )
  }

  private fun setupButtons() = withViewBinding {
    signUpCreateButton.setOnClickListener {
      signUpCreateButton.disable()
      signUpProgressBar.visible()
      viewModel.signUp(
          signUpUserEditText.text.toString(),
          signUpMailEditText.text.toString(),
          signUpPassEditText.text.toString()
      )
    }
  }
}