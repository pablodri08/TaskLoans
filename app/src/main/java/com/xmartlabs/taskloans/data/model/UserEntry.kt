package com.xmartlabs.taskloans.data.model

import com.xmartlabs.taskloans.data.model.service.PagingResponseData

data class UserEntry(
    val userId: String,
    val entry: PagingResponseData,
)
