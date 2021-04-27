package com.xmartlabs.taskloans.data.common

class InvalidUserException(message: String, cause: Throwable) : RuntimeException(message, cause)

class TokenExpiredException(message: String, cause: Throwable) : RuntimeException(message, cause)

class ServerException(message: String, cause: Throwable) : RuntimeException(message, cause)

class UserConflictException(message: String, cause: Throwable) : RuntimeException(message, cause)
