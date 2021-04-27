package com.xmartlabs.taskloans.ui.screens.dashboard

import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xmartlabs.taskloans.R
import com.xmartlabs.taskloans.ui.screens.dashboard.tabs.HistoryFragment
import com.xmartlabs.taskloans.ui.screens.dashboard.tabs.HomeFragment
import com.xmartlabs.taskloans.ui.screens.dashboard.tabs.TeamFragment

class DashboardViewPageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

  override fun getItemCount() = DashboardTabItem.values().size

  override fun createFragment(position: Int) = when (DashboardTabItem.values()[position]) {
    DashboardTabItem.HOME -> HomeFragment()
    DashboardTabItem.HISTORY -> HistoryFragment()
    DashboardTabItem.TEAM -> TeamFragment()
  }
}

enum class DashboardTabItem(@DrawableRes val iconResId: Int) {
  HOME(R.drawable.tab_home),
  HISTORY(R.drawable.tab_history),
  TEAM(R.drawable.tab_team),
}
