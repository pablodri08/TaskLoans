package com.xmartlabs.taskloans.ui.screens.dashboard.tabs

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import com.xmartlabs.swissknife.core.extensions.getColorCompat
import com.xmartlabs.swissknife.core.extensions.getDrawableCompat
import com.xmartlabs.swissknife.core.extensions.gone
import com.xmartlabs.swissknife.core.extensions.invisible
import com.xmartlabs.swissknife.core.extensions.visible
import com.xmartlabs.taskloans.R
import com.xmartlabs.taskloans.data.common.ServerException
import com.xmartlabs.taskloans.data.common.TokenExpiredException
import com.xmartlabs.taskloans.data.model.service.BalanceResponse
import com.xmartlabs.taskloans.databinding.FragmentHomeBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment
import com.xmartlabs.taskloans.ui.common.extensions.observeStateResult
import com.xmartlabs.taskloans.ui.screens.dashboard.DashboardFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseViewBindingFragment<FragmentHomeBinding>() {
  private val viewModel: DashboardFragmentViewModel by viewModel()
  private var adapter: HomeAdapter = HomeAdapter()

  override fun inflateViewBinding(): FragmentHomeBinding =
      FragmentHomeBinding.inflate(layoutInflater)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setUpRecyclerView()
    loadHomeList()
  }

  override fun onDestroyView() = withViewBinding {
    homeRecyclerView.adapter = null
    super.onDestroyView()
  }

  private fun setUpRecyclerView() = withViewBinding {
    homeRecyclerView.adapter = adapter
    val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
    dividerItemDecoration.setDrawable(requireContext().getDrawableCompat(R.drawable.item_divider_withmarging)!!)
    homeRecyclerView.addItemDecoration(dividerItemDecoration)
  }

  private fun loadHomeList() = withViewBinding {
    homeProgressIndicator.visible()
    viewModel.balanceLiveData.observeStateResult(viewLifecycleOwner,
        onFailure = { throwable ->
          homeProgressIndicator.gone()
          checkThrowable(throwable)
        },
        onSuccess = { balanceList ->
          homeProgressIndicator.gone()
          noEntriesTextField.invisible()
          updateUI(balanceList)
        }
    )
  }

  private fun updateUI(balanceList: List<BalanceResponse>) = withViewBinding {
    if (balanceList.isNullOrEmpty()) {
      noEntriesTextField.visible()
      noEntriesTextField.text = getString(R.string.text_history_no_entries)
      noEntriesTextField.setTextColor(requireContext().getColorCompat(R.color.black))
    } else {
      val totalBalance = balanceList.sumBy { it.favour - it.against }
      val cardText = "$totalBalance ${getString(R.string.text_card_home_lavadas)}"
      val negativeCardText = "-$cardText"
      val positiveCardText = "+$cardText"
      with(homeCardText) {
        text = when {
          totalBalance > 0 -> positiveCardText
          totalBalance < 0 -> negativeCardText
          else -> cardText
        }
      }
      adapter.submitList(balanceList)
    }
  }

  private fun checkThrowable(throwable: Throwable) {
    if (throwable is TokenExpiredException) {
      displayError(getString(R.string.text_toast_error_token))
    } else if (throwable is ServerException) {
      displayError(getString(R.string.text_toast_error_server))
    }
  }
}
