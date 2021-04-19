package com.xmartlabs.taskloans.data.model.service

import com.xmartlabs.taskloans.data.model.DataEntry
import com.xmartlabs.taskloans.data.model.PagesEntries

data class EntriesResponse(
    var data: List<DataEntry>?,
    var meta: PagesEntries,
)
