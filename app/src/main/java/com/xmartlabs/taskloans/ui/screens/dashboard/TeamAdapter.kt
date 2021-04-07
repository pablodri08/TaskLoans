package com.xmartlabs.taskloans.ui.screens.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xmartlabs.taskloans.databinding.ListItemTeamBinding

class TeamAdapter : ListAdapter<String, TeamAdapter.TeamHolder>(DiffCallback()) {

  private class DiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(
        @NonNull oldUser: String,
        @NonNull newUser: String
    ): Boolean {
      return oldUser === newUser
    }

    override fun areContentsTheSame(
        @NonNull oldUser: String,
        @NonNull newUser: String
    ): Boolean {
      return oldUser == newUser
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TeamHolder(
      ListItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
  )

  override fun onBindViewHolder(holder: TeamHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class TeamHolder(
      private val itemBinding: ListItemTeamBinding
  ) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(data: String) {
      itemBinding.userName.text = data
    }
  }
}
