package com.xmartlabs.taskloans.ui.screens.signin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.xmartlabs.swissknife.core.extensions.disable
import com.xmartlabs.swissknife.core.extensions.enable
import com.xmartlabs.swissknife.core.extensions.gone
import com.xmartlabs.swissknife.core.extensions.visible
import com.xmartlabs.taskloans.databinding.FragmentSigninBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment
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
          Toast.makeText(
              requireContext(),
              "Password or Email is wrong!",
              Toast.LENGTH_SHORT
          ).show()
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
      signInEnterButton.disable()
      signInProgressBar.visible()
      viewModel.signIn(
          signInUserTextField.editText?.text.toString(),
          signInPasswordTextField.editText?.text.toString()
      )
    }
  }
}
