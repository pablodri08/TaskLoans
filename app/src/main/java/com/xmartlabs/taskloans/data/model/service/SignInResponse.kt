package com.xmartlabs.taskloans.data.model.service

import com.xmartlabs.taskloans.data.model.User

/**
 * Created by mirland on 03/05/20.
 */
data class SignInResponse(val token: String, val user: User)
