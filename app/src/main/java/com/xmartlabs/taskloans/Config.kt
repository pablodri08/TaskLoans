package com.xmartlabs.taskloans

import java.util.Locale

/**
 * Created by mirland on 28/04/20.
 */
object Config {
  const val PROD: Boolean = "dev" != BuildConfig.FLAVOR &&
      "qa" != BuildConfig.FLAVOR &&
      "staging" != BuildConfig.FLAVOR

  val DEBUG: Boolean = BuildConfig.DEBUG

  val ANDROID_SYSTEM_LOG_ENABLED = DEBUG || !PROD

  val STETHO_ENABLED = DEBUG || !PROD

  val CRASHLYTICS_LOG_ENABLED = !DEBUG

  val SHARED_PREFERENCES_NAME = BuildConfig.APP_NAME.toFileName()
  val DB_NAME = BuildConfig.APP_NAME.toFileName()

  const val API_BASE_URL = BuildConfig.API_BASE_URL
}

private fun String.toFileName() = trim()
    .replace(" +", " ")
    .toLowerCase(Locale.ROOT)
    .replace(" ", "_")
