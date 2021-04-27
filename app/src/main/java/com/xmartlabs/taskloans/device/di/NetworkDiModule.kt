package com.xmartlabs.taskloans.device.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.xmartlabs.taskloans.Config
import com.xmartlabs.taskloans.data.service.AuthServiceApi
import com.xmartlabs.taskloans.data.service.LocationServiceApi
import com.xmartlabs.taskloans.data.service.NetworkDebugInterceptors
import com.xmartlabs.taskloans.data.service.NetworkLayerCreator
import com.xmartlabs.taskloans.data.service.TaskServiceApi
import com.xmartlabs.taskloans.data.service.interceptors.AuthInterceptor
import okhttp3.Interceptor
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by mirland on 28/04/20.
 */
object NetworkDiModule {
  @RequiresApi(Build.VERSION_CODES.O)
  val network = module {
    single { get<Retrofit>().create(LocationServiceApi::class.java) }
    single { get<Retrofit>().create(AuthServiceApi::class.java) }
    single { get<Retrofit>().create(TaskServiceApi::class.java) }
    single {
      val debugInterceptors = NetworkDebugInterceptors.createDebugInterceptors(
          useOkHttpInterceptor = Config.ANDROID_SYSTEM_LOG_ENABLED,
          useCurlInterceptor = Config.ANDROID_SYSTEM_LOG_ENABLED,
          useStethoInterceptor = Config.STETHO_ENABLED
      )
      val sessionInterceptors = listOf<Interceptor>(AuthInterceptor(get()))
      NetworkLayerCreator.createRetrofitInstance(Config.API_BASE_URL,
          sessionInterceptors + debugInterceptors)
    }
  }
}
