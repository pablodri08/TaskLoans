package com.xmartlabs.taskloans.data.model.service

import java.util.Date

data class EntriesRequest(
    val recipients: List<String>,
    val date: Date,
)
