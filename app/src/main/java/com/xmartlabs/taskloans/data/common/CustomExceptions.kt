package com.xmartlabs.taskloans.data.common

import java.lang.RuntimeException

class InvalidUserException(message: String, cause: Throwable) : RuntimeException(message, cause)

class TokenExpiredException(message: String, cause: Throwable) : RuntimeException(message, cause)

class ServerException(message: String, cause: Throwable) : RuntimeException(message, cause)

class UserConflictException(message: String, cause: Throwable) : RuntimeException(message, cause)
