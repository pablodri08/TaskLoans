package com.xmartlabs.taskloans.device.di

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.room.Room
import com.xmartlabs.swissknife.datastore.DataStoreSource
import com.xmartlabs.taskloans.Config
import com.xmartlabs.taskloans.data.repository.auth.UserLocalSource
import com.xmartlabs.taskloans.data.repository.auth.UserRemoteSource
import com.xmartlabs.taskloans.data.repository.location.LocationLocalSource
import com.xmartlabs.taskloans.data.repository.location.LocationRemoteSource
import com.xmartlabs.taskloans.data.repository.session.SessionLocalSource
import com.xmartlabs.taskloans.data.repository.store.datastorage.GsonDataStoreEntitySerializer
import com.xmartlabs.taskloans.data.repository.store.db.AppDatabase
import com.xmartlabs.taskloans.data.repository.task.TaskRemoteSource
import com.xmartlabs.taskloans.domain.repository.LocationRepository
import com.xmartlabs.taskloans.domain.repository.SessionRepository
import com.xmartlabs.taskloans.domain.repository.TaskRepository
import com.xmartlabs.taskloans.domain.repository.UserRepository
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
    single { UserRemoteSource(get()) }
    single { SessionLocalSource(get()) }
    single { TaskRemoteSource(get()) }
  }
  val repositories = module {
    single { LocationRepository(get(), get()) }
    single { UserRepository(get(), get(), get()) }
    single { SessionRepository(get()) }
    single { TaskRepository(get()) }
  }
}
