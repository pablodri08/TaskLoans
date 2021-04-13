package com.xmartlabs.taskloans.ui.screens.dashboard.tabs

import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xmartlabs.swissknife.core.extensions.layoutInflater
import com.xmartlabs.taskloans.R
import com.xmartlabs.taskloans.data.model.EntryUserRole
import com.xmartlabs.taskloans.databinding.ListItemHistoryBinding
import kotlinx.android.synthetic.main.list_item_history.view.*

class HistoryAdapter : ListAdapter<EntryUserRole, HistoryAdapter.HistoryHolder>(HistoryAdapter.DiffCallback()) {

  private class DiffCallback : DiffUtil.ItemCallback<EntryUserRole>() {
    override fun areItemsTheSame(
        @NonNull oldUser: EntryUserRole,
        @NonNull newUser: EntryUserRole,
    ): Boolean = oldUser.entryId == newUser.entryId

    override fun areContentsTheSame(
        @NonNull oldUser: EntryUserRole,
        @NonNull newUser: EntryUserRole,
    ): Boolean = oldUser.name == newUser.name
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HistoryHolder(
      ListItemHistoryBinding.inflate(parent.context.layoutInflater, parent, false)
  )

  override fun onBindViewHolder(holder: HistoryAdapter.HistoryHolder, position: Int) = holder.bind(getItem(position))

  inner class HistoryHolder(
      private val itemBinding: ListItemHistoryBinding,
  ) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(data: EntryUserRole) {
      if (data.role == HistoryFragment.UserRole.RECIPIENT) {
        val yellowTransfer = R.drawable.yellow_transfer
        val yellowWash = R.drawable.yellow_wash
        setImages(yellowTransfer, yellowWash)
      } else {
        val violetTransfer = R.drawable.violet_transfer
        val violetWash = R.drawable.violet_wash
        setImages(violetTransfer, violetWash)
      }
      itemBinding.historyCardLayout.history_card_text_name.text = data.name
    }

    private fun setImages(imageTransfer: Int, imageWash: Int) {
      itemBinding.historyCardLayout.history_card_image2.setBackgroundResource(imageTransfer)
      itemBinding.historyCardLayout.history_card_image3.setBackgroundResource(imageWash)
      itemBinding.historyCardLayout.history_card_image4.setBackgroundResource(imageTransfer)
    }
  }
}
