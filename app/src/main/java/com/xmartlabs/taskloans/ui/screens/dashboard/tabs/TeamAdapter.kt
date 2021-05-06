package com.xmartlabs.taskloans.ui.screens.dashboard.tabs

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xmartlabs.swissknife.core.extensions.layoutInflater
import com.xmartlabs.taskloans.data.model.service.UserResponse
import com.xmartlabs.taskloans.databinding.ListItemTeamBinding

class TeamAdapter : ListAdapter<UserResponse, TeamAdapter.TeamHolder>(DiffCallback()) {

  private class DiffCallback : DiffUtil.ItemCallback<UserResponse>() {
    override fun areItemsTheSame(
        oldUser: UserResponse,
        newUser: UserResponse,
    ): Boolean = oldUser.id == newUser.id

    override fun areContentsTheSame(
        oldUser: UserResponse,
        newUser: UserResponse,
    ): Boolean = oldUser.name == newUser.name
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TeamHolder(
      ListItemTeamBinding.inflate(parent.context.layoutInflater, parent, false)
  )

  override fun onBindViewHolder(holder: TeamHolder, position: Int) = holder.bind(getItem(position))

  inner class TeamHolder(
      private val itemBinding: ListItemTeamBinding,
  ) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(data: UserResponse) {
      itemBinding.userName.text = data.name
    }
  }
}
