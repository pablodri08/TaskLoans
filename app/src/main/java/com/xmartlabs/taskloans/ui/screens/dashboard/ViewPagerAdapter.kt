package com.xmartlabs.taskloans.ui.screens.dashboard

import androidx.annotation.DrawableRes
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xmartlabs.taskloans.R
import com.xmartlabs.taskloans.ui.screens.dashboard.tabs.HistoryFragment
import com.xmartlabs.taskloans.ui.screens.dashboard.tabs.HomeFragment
import com.xmartlabs.taskloans.ui.screens.dashboard.tabs.TeamFragment

class ViewPageAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
  companion object {
    private const val TAB_ITEMS = 3
  }

  override fun getItemCount() = TAB_ITEMS

  override fun createFragment(position: Int) = when (TabItem.values()[position]) {
    TabItem.HOME -> HomeFragment()
    TabItem.HISTORY -> HistoryFragment()
    TabItem.TEAM -> TeamFragment()
  }
}

enum class TabItem(@DrawableRes val iconResId: Int) {
  HOME(R.drawable.tab_1),
  HISTORY(R.drawable.tab_2),
  TEAM(R.drawable.tab_3),
}