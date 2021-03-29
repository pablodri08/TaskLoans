package com.xmartlabs.taskloans.ui.screens.dashboard.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xmartlabs.taskloans.R
import com.xmartlabs.taskloans.databinding.FragmentHistoryBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment

class HistoryFragment : BaseViewBindingFragment<FragmentHistoryBinding>() {

  override fun inflateViewBinding(): FragmentHistoryBinding =
      FragmentHistoryBinding.inflate(layoutInflater)

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_history, container, false)
  }
}
