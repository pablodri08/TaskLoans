package com.xmartlabs.taskloans.ui.screens.dashboard.tabs

import android.graphics.Color.GREEN
import android.graphics.Color.RED
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xmartlabs.swissknife.core.extensions.layoutInflater
import com.xmartlabs.taskloans.data.model.service.BalanceResponse
import com.xmartlabs.taskloans.databinding.ListItemHomeBinding

class HomeAdapter : ListAdapter<BalanceResponse, HomeAdapter.HomeHolder>(DiffCallback()) {

  private class DiffCallback : DiffUtil.ItemCallback<BalanceResponse>() {
    override fun areItemsTheSame(
        @NonNull oldBalance: BalanceResponse,
        @NonNull newBalance: BalanceResponse,
    ): Boolean = oldBalance.user.id == newBalance.user.id

    override fun areContentsTheSame(
        @NonNull oldBalance: BalanceResponse,
        @NonNull newBalance: BalanceResponse,
    ): Boolean = oldBalance.user.name == newBalance.user.name
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeHolder(
      ListItemHomeBinding.inflate(parent.context.layoutInflater, parent, false)
  )

  override fun onBindViewHolder(holder: HomeHolder, position: Int) = holder.bind(getItem(position))

  inner class HomeHolder(
      private val itemBinding: ListItemHomeBinding,
  ) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(data: BalanceResponse) {
      itemBinding.userName.text = data.user.name
      val balance = data.favour - data.against
      itemBinding.textViewWashDifferential.text = balance.toString()
      setDifferentialColor(balance)
    }

    private fun setDifferentialColor(balance: Int) = with(itemBinding) {
      if (balance >= 0) textViewWashDifferential.setTextColor(GREEN)
      else textViewWashDifferential.setTextColor(RED)
    }
  }
}
