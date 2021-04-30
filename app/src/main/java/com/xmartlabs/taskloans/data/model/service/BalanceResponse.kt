package com.xmartlabs.taskloans.data.model.service

data class BalanceResponse(
    val user: UserResponse,
    val favour: Int,
    val against: Int,
)
