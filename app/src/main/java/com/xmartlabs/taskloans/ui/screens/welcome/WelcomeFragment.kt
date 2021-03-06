package com.xmartlabs.taskloans.ui.screens.welcome

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.xmartlabs.taskloans.databinding.FragmentWelcomeBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment
import com.xmartlabs.taskloans.ui.common.extensions.observeFinishSuccessResult
import com.xmartlabs.taskloans.ui.common.extensions.observeSuccessResult
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by mirland on 25/04/20.
 */
class WelcomeFragment : BaseViewBindingFragment<FragmentWelcomeBinding>() {
  private val viewModel: WelcomeFragmentViewModel by viewModel()

  override fun inflateViewBinding(): FragmentWelcomeBinding = FragmentWelcomeBinding.inflate(layoutInflater)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupScreenToolbar(viewBinding.toolbar, hasUpButton = false)
    setupViewModel()
  }

  @SuppressLint("SetTextI18n")
  private fun setupViewModel() = with(viewModel) {
    userLiveData.observeFinishSuccessResult(viewLifecycleOwner) { user ->
      viewBinding.titleTextView.text = "Hi ${requireNotNull(user).name}"
    }
    locationLiveData.observeSuccessResult(viewLifecycleOwner) { location ->
      val locationName = listOfNotNull(location.city, location.country)
          .joinToString(", ")
      if (locationName.isNotBlank()) {
        viewBinding.locationTextView.text = "You signed in from $locationName!"
      }
    }
  }
}
