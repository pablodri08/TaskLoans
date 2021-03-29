package com.xmartlabs.taskloans.ui.screens.dashboard

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.xmartlabs.taskloans.databinding.FragmentDashboardBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment
import com.xmartlabs.taskloans.ui.common.extensions.observeFinishSuccessResult
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : BaseViewBindingFragment<FragmentDashboardBinding>() {
  private val viewModel: DashboardFragmentViewModel by viewModel()
  private val adapter by lazy { ViewPageAdapter(requireActivity()) }

  override fun inflateViewBinding(): FragmentDashboardBinding =
      FragmentDashboardBinding.inflate(layoutInflater)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    setupScreenToolbar(viewBinding.toolbar, hasUpButton = false)
    setupViewModel()
    pager.adapter = adapter
    setupTabLayoutMediator()
  }

  private fun setupViewModel() = with(viewModel) {
    userLiveData.observeFinishSuccessResult(viewLifecycleOwner) { user ->
      viewBinding.toolbar.title = user?.name
    }
  }

  private fun setupTabLayoutMediator() {
    val tabLayoutMediator = TabLayoutMediator(tabLayout, pager) { tab, position ->
      when (TabItem.values()[position]) {
        TabItem.HOME -> tab.setIcon(TabItem.HOME.iconResId)
        TabItem.HISTORY -> tab.setIcon(TabItem.HISTORY.iconResId)
        TabItem.TEAM -> tab.setIcon(TabItem.TEAM.iconResId)
      }
    }
    tabLayoutMediator.attach()
  }
}
