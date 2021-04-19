package com.xmartlabs.taskloans.ui.screens.dashboard.tabs

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.flatMap
import androidx.recyclerview.widget.DividerItemDecoration
import com.xmartlabs.swissknife.core.extensions.disable
import com.xmartlabs.swissknife.core.extensions.getDrawableCompat
import com.xmartlabs.swissknife.core.extensions.gone
import com.xmartlabs.swissknife.core.extensions.visible
import com.xmartlabs.taskloans.R
import com.xmartlabs.taskloans.data.model.EntryUI
import com.xmartlabs.taskloans.data.model.UserEntry
import com.xmartlabs.taskloans.databinding.FragmentHistoryBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment
import com.xmartlabs.taskloans.ui.screens.dashboard.DashboardFragmentViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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
    try {
      lifecycleScope.launch {
        viewModel.entries.collectLatest { pagingData ->
          setupUI(pagingData)
        }
      }
    } catch (e: Exception) {
      displayError(e.message!!)
    }
  }

  private suspend fun setupUI(pagingData: PagingData<UserEntry>) = withViewBinding {
    historyProgressIndicator.gone()
    textfieldNoEntries.disable()
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
