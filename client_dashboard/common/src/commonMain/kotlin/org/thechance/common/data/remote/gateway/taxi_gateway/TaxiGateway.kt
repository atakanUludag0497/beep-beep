package org.thechance.common.data.remote.gateway.taxi_gateway

import org.thechance.common.domain.entity.AddTaxi
import org.thechance.common.domain.entity.DataWrapper
import org.thechance.common.domain.entity.Taxi

class TaxiGateway:ITaxiGateway {
    override suspend fun getTaxis(): DataWrapper<Taxi> {
        TODO("Not yet implemented")
    }

    override suspend fun createTaxi(taxi: AddTaxi): Taxi {
        TODO("Not yet implemented")
    }

    override suspend fun searchTaxiByDriverUsername(driverUsername: String): DataWrapper<Taxi> {
        TODO("Not yet implemented")
    }
}