package com.xmartlabs.taskloans.device.di

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.room.Room
import com.xmartlabs.taskloans.Config
import com.xmartlabs.taskloans.data.repository.auth.UserLocalSource
import com.xmartlabs.taskloans.data.repository.auth.UserRemoteSource
import com.xmartlabs.taskloans.data.repository.location.LocationLocalSource
import com.xmartlabs.taskloans.data.repository.location.LocationRemoteSource
import com.xmartlabs.taskloans.data.repository.session.SessionLocalSource
import com.xmartlabs.taskloans.data.repository.store.datastorage.GsonDataStoreEntitySerializer
import com.xmartlabs.taskloans.data.repository.store.db.AppDatabase
import com.xmartlabs.taskloans.domain.repository.LocationRepository
import com.xmartlabs.taskloans.domain.repository.SessionRepository
import com.xmartlabs.taskloans.domain.repository.UserRepository
import com.xmartlabs.swissknife.datastore.DataStoreSource
import org.koin.dsl.module

/**
 * Created by mirland on 25/04/20.
 */
object RepositoryDiModuleProvider {
  val stores = module {
    single {
      DataStoreSource(
          dataStore = get<Context>().createDataStore(name = Config.SHARED_PREFERENCES_NAME),
          serializer = GsonDataStoreEntitySerializer()
      )
    }
    single {
      Room.databaseBuilder(get(), AppDatabase::class.java, Config.DB_NAME)
          .build()
    }
    single { get<AppDatabase>().locationDao() }
  }
  val sources = module {
    single { LocationRemoteSource(get()) }
    single { LocationLocalSource(get()) }
    single { UserLocalSource() }
    single { UserRemoteSource() }
    single { SessionLocalSource(get()) }
  }
  val repositories = module {
    single { LocationRepository(get(), get()) }
    single { UserRepository(get(), get(), get()) }
    single { SessionRepository(get()) }
  }
}
