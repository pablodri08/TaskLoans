package com.xmartlabs.taskloans.ui.screens.dashboard

import android.os.Bundle
import android.view.View
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.xmartlabs.taskloans.databinding.FragmentDashboardBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment
import com.xmartlabs.taskloans.ui.common.extensions.observeFinishSuccessResult
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : BaseViewBindingFragment<FragmentDashboardBinding>() {
  private val viewModel: DashboardFragmentViewModel by viewModel()
  private lateinit var adapter : FragmentStateAdapter

  override fun inflateViewBinding(): FragmentDashboardBinding =
      FragmentDashboardBinding.inflate(layoutInflater)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    adapter = DashboardViewPageAdapter(this)
    setupScreenToolbar(viewBinding.toolbar, hasUpButton = false)
    setupViewModel()
    setupViewPager()
  }

  private fun setupViewModel() = with(viewModel) {
    userLiveData.observeFinishSuccessResult(viewLifecycleOwner) { user ->
      viewBinding.toolbar.title = user?.name
    }
  }

  private fun setupViewPager() = withViewBinding {
    pager.adapter = adapter
    val tabLayoutMediator = TabLayoutMediator(tabLayout, pager) { tab, position ->
      tab.setIcon(DashboardTabItem.values()[position].iconResId)
    }
    tabLayoutMediator.attach()
  }
}
