package com.xmartlabs.taskloans.data.repository.common

class InvalidUserException(message: String) : Exception(message)

class ServerException(message: String) : Exception(message)

class UserConflictException(message: String) : Exception(message)
