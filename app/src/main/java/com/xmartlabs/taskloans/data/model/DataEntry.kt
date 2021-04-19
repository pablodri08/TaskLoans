package com.xmartlabs.taskloans.data.model

import com.xmartlabs.taskloans.data.model.service.UserResponse
import java.util.Date

data class DataEntry(
    var id: String?,
    var date: Date?,
    var note: String?,
    var performer: UserResponse?,
    var recipients: List<UserResponse>?,
)
