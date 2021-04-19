package com.xmartlabs.taskloans.ui.screens.dashboard.tabs

import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xmartlabs.swissknife.core.extensions.layoutInflater
import com.xmartlabs.taskloans.R
import com.xmartlabs.taskloans.data.model.EntryUI
import com.xmartlabs.taskloans.databinding.ListItemHistoryBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoryAdapter : PagingDataAdapter<EntryUI, HistoryAdapter.HistoryHolder>(DiffCallback()) {
  companion object {
    const val DATEFORMAT = "d MMM"
  }

  private class DiffCallback : DiffUtil.ItemCallback<EntryUI>() {
    override fun areItemsTheSame(
        @NonNull oldUser: EntryUI,
        @NonNull newUser: EntryUI,
    ): Boolean = oldUser.entryId == newUser.entryId

    override fun areContentsTheSame(
        @NonNull oldUser: EntryUI,
        @NonNull newUser: EntryUI,
    ): Boolean = oldUser.name == newUser.name
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HistoryHolder(
      ListItemHistoryBinding.inflate(parent.context.layoutInflater, parent, false)
  )

  override fun onBindViewHolder(holder: HistoryAdapter.HistoryHolder, position: Int) = holder.bind(getItem(position)!!)

  inner class HistoryHolder(
      private val itemBinding: ListItemHistoryBinding,
  ) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(data: EntryUI) {
      setCardDate(data.entryDate)
      if (data.role == HistoryFragment.UserRole.RECIPIENT) {
        val yellowTransfer = R.drawable.yellow_transfer
        val yellowWash = R.drawable.yellow_wash
        setImages(yellowTransfer, yellowWash)
      } else {
        val violetTransfer = R.drawable.violet_transfer
        val violetWash = R.drawable.violet_wash
        setImages(violetTransfer, violetWash)
      }
      itemBinding.historyCardTextName.text = data.name
    }

    private fun setCardDate(date: Date?) {
      if (date != null) {
        val customDate = SimpleDateFormat(DATEFORMAT, Locale.US).format(date)
        itemBinding.dateHistoryCard.text = customDate.toString()
      }
    }

    private fun setImages(imageTransfer: Int, imageWash: Int) {
      itemBinding.historyCardImage2.setBackgroundResource(imageTransfer)
      itemBinding.historyCardImage3.setBackgroundResource(imageWash)
      itemBinding.historyCardImage4.setBackgroundResource(imageTransfer)
    }
  }
}
