package com.xmartlabs.taskloans.ui.screens.dashboard.tabs

import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.paging.flatMap
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xmartlabs.swissknife.core.extensions.layoutInflater
import com.xmartlabs.taskloans.R
import com.xmartlabs.taskloans.data.model.UserEntry
import com.xmartlabs.taskloans.databinding.ListItemHistoryBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HistoryAdapter : PagingDataAdapter<HistoryAdapter.EntryUI, HistoryAdapter.HistoryHolder>(DiffCallback()) {
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

  suspend fun submitEntries(pagingData: PagingData<UserEntry>) {
    val entries = pagingData.flatMap {
      if (it.entry.performer.id == it.userId) {
        it.entry.recipients.map { recipient ->
          EntryUI(recipient.name, UserRole.RECIPIENT, it.entry.id, it.entry.date)
        }
      } else {
        listOf(EntryUI(it.entry.performer.name, UserRole.PERFORMER, it.entry.id, it.entry.date))
      }
    }
    submitData(entries)
  }

  inner class HistoryHolder(
      private val itemBinding: ListItemHistoryBinding,
  ) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(data: EntryUI) {
      setCardDate(data.entryDate)
      setImages(data.role.transferImage, data.role.washImage)
      itemBinding.historyCardTextName.text = data.name
    }

    private fun setCardDate(date: LocalDateTime?) {
      if (date != null) {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(DATEFORMAT)
        itemBinding.dateHistoryCard.text = date.format(formatter)
      }
    }

    private fun setImages(imageTransfer: Int, imageWash: Int) = with(itemBinding) {
      historyCardImage2.setBackgroundResource(imageTransfer)
      historyCardImage3.setBackgroundResource(imageWash)
      historyCardImage4.setBackgroundResource(imageTransfer)
    }
  }

  private val UserRole.transferImage: Int
    get() = when (this) {
      UserRole.PERFORMER -> R.drawable.violet_transfer
      UserRole.RECIPIENT -> R.drawable.yellow_transfer
    }

  private val UserRole.washImage: Int
    get() = when (this) {
      UserRole.PERFORMER -> R.drawable.violet_wash
      UserRole.RECIPIENT -> R.drawable.yellow_wash
    }

  data class EntryUI(
      val name: String,
      val role: UserRole,
      var entryId: String,
      var entryDate: LocalDateTime,
  )

  enum class UserRole {
    PERFORMER,
    RECIPIENT
  }
}
