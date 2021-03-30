package com.xmartlabs.taskloans.ui.screens.dashboard.tabs

import com.xmartlabs.taskloans.databinding.FragmentHomeBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment

class HomeFragment : BaseViewBindingFragment<FragmentHomeBinding>() {

  override fun inflateViewBinding(): FragmentHomeBinding =
      FragmentHomeBinding.inflate(layoutInflater)
}
