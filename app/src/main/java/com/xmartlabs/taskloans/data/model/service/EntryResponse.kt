package com.xmartlabs.taskloans.data.model.service

import com.xmartlabs.taskloans.data.model.Entry
import com.xmartlabs.taskloans.data.model.MethaResponse

data class EntryResponse(
    var data: List<Entry>?,
    var meta: MethaResponse,
)
