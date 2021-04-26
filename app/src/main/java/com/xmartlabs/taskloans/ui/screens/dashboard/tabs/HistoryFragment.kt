package com.xmartlabs.taskloans.ui.screens.dashboard.tabs

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.xmartlabs.swissknife.core.extensions.getColorCompat
import com.xmartlabs.swissknife.core.extensions.gone
import com.xmartlabs.swissknife.core.extensions.invisible
import com.xmartlabs.swissknife.core.extensions.visible
import com.xmartlabs.taskloans.R
import com.xmartlabs.taskloans.data.common.ServerException
import com.xmartlabs.taskloans.data.common.TokenExpiredException
import com.xmartlabs.taskloans.databinding.FragmentHistoryBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment
import com.xmartlabs.taskloans.ui.screens.dashboard.DashboardFragmentViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BaseViewBindingFragment<FragmentHistoryBinding>() {
  private val viewModel: DashboardFragmentViewModel by viewModel()
  private var adapter: HistoryAdapter = HistoryAdapter()

  override fun inflateViewBinding(): FragmentHistoryBinding =
      FragmentHistoryBinding.inflate(layoutInflater)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) = withViewBinding {
    super.onViewCreated(view, savedInstanceState)
    historyRecyclerView.adapter = adapter
    loadHistoryList()
  }

  override fun onDestroyView() = withViewBinding {
    historyRecyclerView.adapter = null
    super.onDestroyView()
  }

  private fun loadHistoryList() = withViewBinding {
    historyProgressIndicator.visible()
    setAdapterListener()
    try {
      lifecycleScope.launch {
        viewModel.entries.collect { pagingData ->
          noEntriesTextField.invisible()
          adapter.submitEntries(pagingData)
        }
      }
    } catch (exception: Exception) {
      if (exception is TokenExpiredException) {
        displayError(getString(R.string.text_toast_error_token))
      } else if (exception is ServerException) {
        displayError(getString(R.string.text_toast_error_server))
      }
    }
  }

  private fun setAdapterListener() = withViewBinding {
    adapter.addLoadStateListener { loadState ->
      if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached) {
        historyProgressIndicator.gone()
        if (adapter.itemCount < 1) {
          noEntriesTextField.visible()
          noEntriesTextField.text = getString(R.string.text_history_no_entries)
          noEntriesTextField.setTextColor(requireContext().getColorCompat(R.color.black))
        } else {
          noEntriesTextField.gone()
        }
      }
    }
  }

  private fun displayError(error: String) = Toast.makeText(
      requireContext(),
      error,
      Toast.LENGTH_SHORT
  ).show()
}
