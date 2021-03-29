package com.xmartlabs.taskloans.ui.screens.dashboard.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xmartlabs.taskloans.R
import com.xmartlabs.taskloans.databinding.FragmentHomeBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment

class HomeFragment : BaseViewBindingFragment<FragmentHomeBinding>() {

  override fun inflateViewBinding(): FragmentHomeBinding =
      FragmentHomeBinding.inflate(layoutInflater)
}
