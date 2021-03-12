package com.xmartlabs.taskloans.domain.usecase

import com.xmartlabs.taskloans.data.model.Location
import com.xmartlabs.taskloans.domain.repository.LocationRepository
import com.xmartlabs.taskloans.domain.usecase.common.FlowCoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

/**
 * Created by mirland on 28/04/20.
 */
class GetLocationUseCase(
    private val locationRepository: LocationRepository,
    dispatcher: CoroutineDispatcher
) : FlowCoroutineUseCase<Unit, Location>(dispatcher) {
  override fun execute(params: Unit): Flow<Location> = locationRepository.getLocation()
}
