package com.xmartlabs.taskloans.ui.screens.dashboard.tabs

import com.xmartlabs.taskloans.databinding.FragmentTeamBinding
import com.xmartlabs.taskloans.ui.common.BaseViewBindingFragment

class TeamFragment : BaseViewBindingFragment<FragmentTeamBinding>() {

  override fun inflateViewBinding(): FragmentTeamBinding =
      FragmentTeamBinding.inflate(layoutInflater)
}
