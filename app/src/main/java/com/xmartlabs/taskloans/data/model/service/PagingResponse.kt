package com.xmartlabs.taskloans.data.model.service

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class PagingResponse<T>(
    var data: List<T>,
    var meta: PagingResponseMetadata,
)

data class PagingResponseData(
    var id: String,
    var date: LocalDateTime,
    var note: String,
    var performer: UserResponse,
    var recipients: List<UserResponse>,
)

data class PagingResponseMetadata(
    @SerializedName("total_pages")
    var totalPages: Int,
)
