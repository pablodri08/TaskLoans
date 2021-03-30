package com.xmartlabs.taskloans.ui.screens.dashboard.tabs

import com.xmartlabs.taskloans.databinding.FragmentHistoryBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment

class HistoryFragment : BaseViewBindingFragment<FragmentHistoryBinding>() {

  override fun inflateViewBinding(): FragmentHistoryBinding =
      FragmentHistoryBinding.inflate(layoutInflater)
}
