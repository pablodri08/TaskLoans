package com.xmartlabs.taskloans.data.model

import com.xmartlabs.taskloans.ui.screens.dashboard.tabs.HistoryFragment
import java.util.Date

data class EntryUserRole(
    val name: String,
    val role: HistoryFragment.UserRole,
    var entryId: String? = null,
    var entryDate: Date? = null,
)
