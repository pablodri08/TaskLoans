package com.xmartlabs.taskloans.data.repository

import com.xmartlabs.taskloans.data.common.DataHelper

open class RemoteSource {
  protected suspend fun <T> invokeServiceCall(call: suspend () -> T): T =
      DataHelper.mapServiceError(DataHelper::serviceErrorMapper, call)
}
