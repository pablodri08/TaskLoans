package com.xmartlabs.taskloans.data.repository.location

import com.xmartlabs.taskloans.data.model.Location
import com.xmartlabs.taskloans.data.repository.store.db.LocationDao

/**
 * Created by mirland on 31/05/20.
 */
class LocationLocalSource(private val locationDao: LocationDao) {
  fun getLocation() = locationDao.getLastLocation()

  fun saveLocation(location: Location) = locationDao.insert(location)
}
