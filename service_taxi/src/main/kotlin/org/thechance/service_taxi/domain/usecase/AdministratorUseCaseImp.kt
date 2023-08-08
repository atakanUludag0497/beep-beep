package org.thechance.service_taxi.domain.usecase

import org.koin.core.annotation.Single
import org.thechance.service_taxi.domain.entity.Taxi
import org.thechance.service_taxi.domain.entity.TaxiUpdateRequest
import org.thechance.service_taxi.domain.entity.Trip
import org.thechance.service_taxi.domain.gateway.DataBaseGateway

@Single
class AdministratorUseCaseImp(
        private val dataBaseGateway: DataBaseGateway
) : AdministratorUseCase {
    override suspend fun getTrips(page: Int, limit: Int): List<Trip> {
        return dataBaseGateway.getAllTrips(page, limit)
    }

    override suspend fun deleteTrip(tripId: String): Boolean {
        return dataBaseGateway.deleteTrip(tripId)
    }

    override suspend fun createTaxi(taxi: Taxi): Boolean {
        return dataBaseGateway.addTaxi(taxi)
    }

    override suspend fun deleteTaxi(taxiId: String): Boolean {
        return dataBaseGateway.deleteTaxi(taxiId)
    }

    override suspend fun updateTaxi(taxi: TaxiUpdateRequest): Boolean {
        return dataBaseGateway.updateTaxi(taxi)
    }

    override suspend fun getAllTaxi(page: Int, limit: Int): List<Taxi> {
        return dataBaseGateway.getAllTaxes(page, limit)
    }
}