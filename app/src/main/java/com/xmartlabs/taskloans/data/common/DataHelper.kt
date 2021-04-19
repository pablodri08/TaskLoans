package com.xmartlabs.taskloans.data.common

import retrofit2.HttpException

object DataHelper {
  private const val ERROR_INVALID_USER = 401
  private const val ERROR_INVALID_TOKEN = 404
  private const val ERROR_USER_CONFLICT = 409

  suspend fun <T> mapServiceError(errorMapper: (HttpException) -> Exception, serviceCall: suspend () -> T): T = try {
    serviceCall()
  } catch (e: Exception) {
    if (e is HttpException) {
      throw errorMapper(e)
    } else {
      throw e
    }
  }

  fun serviceErrorMapper(e: HttpException): Exception = when (e.code()) {
    ERROR_INVALID_USER -> InvalidUserException(e.message())
    ERROR_USER_CONFLICT -> UserConflictException(e.message())
    ERROR_INVALID_TOKEN -> TokenExpiredException(e.message())
    else -> ServerException(e.message())
  }
}
