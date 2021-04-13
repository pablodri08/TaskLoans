package com.xmartlabs.taskloans.data.model

/**
 * Created by mirland on 25/04/20.
 */
data class User(
    val id: String,
    val name: String,
    val email: String,
    val tasks: List<Task>,
)
