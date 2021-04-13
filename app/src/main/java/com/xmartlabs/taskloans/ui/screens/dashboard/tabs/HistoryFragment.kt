package com.xmartlabs.taskloans.ui.screens.dashboard.tabs

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.xmartlabs.swissknife.core.extensions.disable
import com.xmartlabs.swissknife.core.extensions.enable
import com.xmartlabs.swissknife.core.extensions.getDrawableCompat
import com.xmartlabs.swissknife.core.extensions.gone
import com.xmartlabs.swissknife.core.extensions.visible
import com.xmartlabs.taskloans.R
import com.xmartlabs.taskloans.data.common.ServerException
import com.xmartlabs.taskloans.data.common.TokenExpiredException
import com.xmartlabs.taskloans.data.model.EntryUserRole
import com.xmartlabs.taskloans.databinding.FragmentHistoryBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment
import com.xmartlabs.taskloans.ui.common.extensions.observeStateResult
import com.xmartlabs.taskloans.ui.screens.dashboard.DashboardFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BaseViewBindingFragment<FragmentHistoryBinding>() {
  private val viewModel: DashboardFragmentViewModel by viewModel()
  private var adapter: HistoryAdapter? = HistoryAdapter()

  override fun inflateViewBinding(): FragmentHistoryBinding =
      FragmentHistoryBinding.inflate(layoutInflater)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setUpRecyclerView()
    loadHistoryList()
  }

  override fun onDetach() {
    super.onDetach()
    adapter = null
  }

  private fun setUpRecyclerView() = withViewBinding {
    historyRecyclerView.adapter = adapter
    val dividerItemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
    dividerItemDecoration.setDrawable(requireContext().getDrawableCompat(R.drawable.item_divider)!!)
    historyRecyclerView.addItemDecoration(dividerItemDecoration)
  }

  private fun loadHistoryList() = withViewBinding {
    historyProgressIndicator.visible()
    viewModel.entriesLiveData.observeStateResult(viewLifecycleOwner,
        onFailure = { throwable ->
          historyProgressIndicator.gone()
          if (throwable is TokenExpiredException) {
            displayError(getString(R.string.text_toast_error_token))
          } else if (throwable is ServerException) {
            displayError(getString(R.string.text_toast_error_server))
          }
        },
        onSuccess = { userIdEntries ->
          historyProgressIndicator.gone()
          if (userIdEntries.entries.isNullOrEmpty()) {
            textfieldNoEntries.enable()
            textfieldNoEntries.text = getString(R.string.text_history_no_entries)
            textfieldNoEntries.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
          } else {
            textfieldNoEntries.disable()
            val entries: List<EntryUserRole> = userIdEntries.entries!!.flatMap { entry ->
              if (entry.performer!!.id == userIdEntries.currentUserId) {
                entry.recipients!!.map { recipient ->
                  EntryUserRole(recipient.name, UserRole.RECIPIENT, entry.id, entry.date)
                }
              } else {
                listOf(EntryUserRole(entry.performer!!.name, UserRole.PERFORMER, entry.id, entry.date))
              }
            }
            updateUI(entries)
          }
        }
    )
  }

  private fun updateUI(usersNames: List<EntryUserRole>) {
    adapter!!.submitList(usersNames)
  }

  private fun displayError(error: String) = Toast.makeText(
      requireContext(),
      error,
      Toast.LENGTH_SHORT
  ).show()

  enum class UserRole {
    PERFORMER,
    RECIPIENT
  }
}
