package com.xmartlabs.taskloans.ui.screens.dashboard.tabs

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.flatMap
import com.xmartlabs.swissknife.core.extensions.disable
import com.xmartlabs.swissknife.core.extensions.getColorCompat
import com.xmartlabs.swissknife.core.extensions.gone
import com.xmartlabs.swissknife.core.extensions.visible
import com.xmartlabs.taskloans.R
import com.xmartlabs.taskloans.data.common.ServerException
import com.xmartlabs.taskloans.data.common.TokenExpiredException
import com.xmartlabs.taskloans.data.model.EntryUI
import com.xmartlabs.taskloans.data.model.UserEntry
import com.xmartlabs.taskloans.databinding.FragmentHistoryBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment
import com.xmartlabs.taskloans.ui.screens.dashboard.DashboardFragmentViewModel
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BaseViewBindingFragment<FragmentHistoryBinding>() {
  private val viewModel: DashboardFragmentViewModel by viewModel()
  private var adapter: HistoryAdapter? = HistoryAdapter()

  override fun inflateViewBinding(): FragmentHistoryBinding =
      FragmentHistoryBinding.inflate(layoutInflater)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) = withViewBinding {
    super.onViewCreated(view, savedInstanceState)
    historyRecyclerView.adapter = adapter
    loadHistoryList()
  }

  override fun onDestroy() {
    historyRecyclerView.adapter = null
    super.onDestroy()
  }

  private fun loadHistoryList() = withViewBinding {
    historyProgressIndicator.visible()
    setAdapterListener()
    try {
      lifecycleScope.launch {
        viewModel.entries.collectLatest { pagingData ->
          setupUI(pagingData)
        }
      }
    } catch (e: Exception) {
      if (e is TokenExpiredException) {
        displayError(getString(R.string.text_toast_error_token))
      } else if (e is ServerException) {
        displayError(getString(R.string.text_toast_error_server))
      }
    }
  }

  private fun setAdapterListener() = withViewBinding {
    adapter?.addLoadStateListener { loadState ->
      if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached) {
        historyProgressIndicator.gone()
        if (adapter!!.itemCount < 1) {
          noEntriesTextField.visible()
          noEntriesTextField.text = getString(R.string.text_history_no_entries)
          noEntriesTextField.setTextColor(requireContext().getColorCompat(R.color.black))
        } else {
          noEntriesTextField.gone()
        }
      }
    }
  }

  private suspend fun setupUI(pagingData: PagingData<UserEntry>) = withViewBinding {
    noEntriesTextField.disable()
    val entries = pagingData.flatMap {
      if (it.entry.performer!!.id == it.userId) {
        it.entry.recipients!!.map { recipient ->
          EntryUI(recipient.name, UserRole.RECIPIENT, it.entry.id, it.entry.date)
        }
      } else {
        listOf(EntryUI(it.entry.performer!!.name, UserRole.PERFORMER, it.entry.id, it.entry.date))
      }
    }
    adapter!!.submitData(entries)
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
