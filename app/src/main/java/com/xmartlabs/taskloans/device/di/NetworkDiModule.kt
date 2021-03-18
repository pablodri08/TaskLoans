package com.xmartlabs.taskloans.device.di

import com.xmartlabs.taskloans.Config
import com.xmartlabs.taskloans.data.service.LocationServiceApi
import com.xmartlabs.taskloans.data.service.NetworkDebugInterceptors
import com.xmartlabs.taskloans.data.service.NetworkLayerCreator
import com.xmartlabs.taskloans.data.service.OutServiceApi
import okhttp3.Interceptor
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by mirland on 28/04/20.
 */
object NetworkDiModule {
  val network = module {
    single { get<Retrofit>().create(LocationServiceApi::class.java) }
    single { get<Retrofit>().create(OutServiceApi::class.java) }
    single {
      val debugInterceptors = NetworkDebugInterceptors.createDebugInterceptors(
          useOkHttpInterceptor = Config.ANDROID_SYSTEM_LOG_ENABLED,
          useCurlInterceptor = Config.ANDROID_SYSTEM_LOG_ENABLED,
          useStethoInterceptor = Config.STETHO_ENABLED
      )
      // TODO: Add session interceptor and refresh token interceptor
      val sessionInterceptors = listOf<Interceptor>()
      NetworkLayerCreator.createRetrofitInstance(Config.API_BASE_URL,
          sessionInterceptors + debugInterceptors)
    }
  }
}
