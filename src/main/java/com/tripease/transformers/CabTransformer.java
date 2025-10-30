package com.tripease.transformers;

import com.tripease.dto.request.CabRequest;
import com.tripease.dto.response.CabResponse;
import com.tripease.entities.Cab;
import com.tripease.entities.Driver;

public class CabTransformer {

    public static Cab cabReqToCab(CabRequest cabRequest) {
        return Cab.builder()
                .cabNumber( cabRequest.getCabNumber() )
                .cabModel(cabRequest.getCabModel() )
                .perKmRate( cabRequest.getPerKmRate() )
                .isAvailable(true)
                .build();
    }

    public static CabResponse cabToCabResponse(Cab cab, Driver driver) {
        return CabResponse.builder()
                .cabNumber(cab.getCabNumber())
                .cabModel(cab.getCabModel())
                .perKmRate(cab.getPerKmRate())
                .isAvailable(cab.isAvailable())
                .driver(DriverTransformer.driverToDriverResponse(driver))
                .build();
    }
}
