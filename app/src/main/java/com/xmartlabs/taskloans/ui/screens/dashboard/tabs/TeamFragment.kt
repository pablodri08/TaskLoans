package com.xmartlabs.taskloans.ui.screens.dashboard.tabs

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import com.xmartlabs.swissknife.core.extensions.getDrawableCompat
import com.xmartlabs.swissknife.core.extensions.gone
import com.xmartlabs.swissknife.core.extensions.visible
import com.xmartlabs.taskloans.R
import com.xmartlabs.taskloans.data.common.ServerException
import com.xmartlabs.taskloans.data.common.TokenExpiredException
import com.xmartlabs.taskloans.data.model.service.UserResponse
import com.xmartlabs.taskloans.databinding.FragmentTeamBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment
import com.xmartlabs.taskloans.ui.common.extensions.observeStateResult
import com.xmartlabs.taskloans.ui.screens.dashboard.DashboardFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TeamFragment : BaseViewBindingFragment<FragmentTeamBinding>() {
  private val viewModel: DashboardFragmentViewModel by viewModel()
  private var adapter: TeamAdapter = TeamAdapter()

  override fun inflateViewBinding(): FragmentTeamBinding =
      FragmentTeamBinding.inflate(layoutInflater)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setUpRecyclerView()
    loadTeamList()
  }

  override fun onDestroyView() = withViewBinding {
    teamRecyclerView.adapter = null
    super.onDestroyView()
  }

  private fun setUpRecyclerView() = withViewBinding {
    teamRecyclerView.adapter = adapter
    val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
    dividerItemDecoration.setDrawable(requireContext().getDrawableCompat(R.drawable.item_divider)!!)
    teamRecyclerView.addItemDecoration(dividerItemDecoration)
  }

  private fun loadTeamList() = withViewBinding {
    teamProgressIndicator.visible()
    viewModel.listUsersLiveData.observeStateResult(viewLifecycleOwner,
        onFailure = { throwable ->
          teamProgressIndicator.gone()
          if (throwable is TokenExpiredException) {
            displayError(getString(R.string.text_toast_error_token))
          } else if (throwable is ServerException) {
            displayError(getString(R.string.text_toast_error_server))
          }
        },
        onSuccess = { userList ->
          teamProgressIndicator.gone()
          updateUI(userList)
        }
    )
  }

  private fun updateUI(usersNames: List<UserResponse>) {
    adapter.submitList(usersNames)
  }

  private fun displayError(error: String) = Toast.makeText(
      requireContext(),
      error,
      Toast.LENGTH_SHORT
  ).show()
}
