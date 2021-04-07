package com.xmartlabs.taskloans.ui.screens.dashboard.tabs

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.DividerItemDecoration
import com.xmartlabs.swissknife.core.extensions.gone
import com.xmartlabs.swissknife.core.extensions.visible
import com.xmartlabs.taskloans.R
import com.xmartlabs.taskloans.data.common.ServerException
import com.xmartlabs.taskloans.data.common.TokenExpiredException
import com.xmartlabs.taskloans.databinding.FragmentTeamBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment
import com.xmartlabs.taskloans.ui.common.extensions.observeStateResult
import com.xmartlabs.taskloans.ui.screens.dashboard.DashboardFragmentViewModel
import com.xmartlabs.taskloans.ui.screens.dashboard.TeamAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TeamFragment : BaseViewBindingFragment<FragmentTeamBinding>() {
  private val viewModel: DashboardFragmentViewModel by viewModel()
  private val adapter: TeamAdapter = TeamAdapter()
  private var _binding: FragmentTeamBinding? = null
  private val binding get() = _binding!!

  override fun inflateViewBinding(): FragmentTeamBinding =
      FragmentTeamBinding.inflate(layoutInflater).also { _binding = it }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setUpRecyclerView()
    loadTeamList()
  }

  override fun onDestroy() {
    super.onDestroy()
    binding.teamRecyclerView.adapter = null
  }

  private fun setUpRecyclerView() {
    binding.teamRecyclerView.adapter = adapter
    val dividerItemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
    dividerItemDecoration.setDrawable(getDrawable(requireContext(), R.drawable.item_divider)!!)
    binding.teamRecyclerView.addItemDecoration(dividerItemDecoration)
  }

  private fun loadTeamList() = with(viewModel) {
    binding.teamProgressIndicator.visible()
    listUsersLiveData.observeStateResult(viewLifecycleOwner,
        onFailure = { throwable ->
          binding.teamProgressIndicator.gone()
          if (throwable is TokenExpiredException) {
            displayError(getString(R.string.text_toast_error_token))
          } else if (throwable is ServerException) {
            displayError(getString(R.string.text_toast_error_server))
          }
        },
        onSuccess = { userList ->
          binding.teamProgressIndicator.gone()
          updateUI(userList.map { user -> user.name })
        }
    )
  }

  private fun updateUI(usersNames: List<String>) {
    adapter.submitList(usersNames)
  }

  private fun displayError(error: String) = Toast.makeText(
      requireContext(),
      error,
      Toast.LENGTH_SHORT
  ).show()
}
