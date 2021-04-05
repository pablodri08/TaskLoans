package com.xmartlabs.taskloans.ui.screens.dashboard.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.xmartlabs.swissknife.core.extensions.gone
import com.xmartlabs.swissknife.core.extensions.visible
import com.xmartlabs.taskloans.R
import com.xmartlabs.taskloans.data.common.ServerException
import com.xmartlabs.taskloans.data.common.TokenExpiredException
import com.xmartlabs.taskloans.databinding.FragmentTeamBinding
import com.xmartlabs.taskloans.databinding.ListItemTeamBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment
import com.xmartlabs.taskloans.ui.common.extensions.observeStateResult
import com.xmartlabs.taskloans.ui.screens.dashboard.DashboardFragmentViewModel
import kotlinx.android.synthetic.main.fragment_team.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TeamFragment : BaseViewBindingFragment<FragmentTeamBinding>() {
  private val viewModel: DashboardFragmentViewModel by viewModel()
  private val adapter: TeamAdapter = TeamAdapter()

  override fun inflateViewBinding(): FragmentTeamBinding =
      FragmentTeamBinding.inflate(layoutInflater)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setUpRecyclerView()
    loadTeamList()
  }

  private fun setUpRecyclerView() {
    teamRecyclerView.adapter = adapter
    val dividerItemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
    dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.item_divider)!!)
    teamRecyclerView.addItemDecoration(dividerItemDecoration)
  }

  private fun loadTeamList() = with(viewModel) {
    team_progressIndicator.visible()
    listUsersLiveData.observeStateResult(viewLifecycleOwner,
        onFailure = { throwable ->
          team_progressIndicator.gone()
          if (throwable is TokenExpiredException) {
            displayError(getString(R.string.text_toast_error_token))
          } else if (throwable is ServerException) {
            displayError(getString(R.string.text_toast_error_server))
          }
        },
        onSuccess = { userList ->
          team_progressIndicator.gone()
          updateUI(userList.map { user -> user.name })
        }
    )
  }

  private fun updateUI(usersNames: List<String>) {
    adapter.usersNames = usersNames
  }

  private inner class TeamHolder(
      private val itemBinding: ListItemTeamBinding
  ) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(userName: String) {
      itemBinding.userName.text = userName
    }
  }

  private inner class TeamAdapter : RecyclerView.Adapter<TeamHolder>() {
    var usersNames: List<String>? = null
      set(value) {
        field = value
        notifyDataSetChanged()
      }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TeamHolder(
        ListItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = usersNames?.size ?: 0

    override fun onBindViewHolder(holder: TeamHolder, position: Int) {
      usersNames?.get(position)?.let { holder.bind(it) }
    }
  }

  private fun displayError(error: String) = Toast.makeText(
      requireContext(),
      error,
      Toast.LENGTH_SHORT
  ).show()
}
