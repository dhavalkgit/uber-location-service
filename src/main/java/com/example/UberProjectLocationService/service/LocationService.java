package com.example.UberProjectLocationService.service;

import com.example.UberProjectLocationService.dto.DriverLocationDto;
import com.example.UberProjectLocationService.dto.NearByDriveRequestDto;
import com.example.UberProjectLocationService.dto.SaveDriverLocationDto;

import java.util.List;

public interface LocationService {
    Boolean saveDriverLocation(SaveDriverLocationDto req);
    List<DriverLocationDto> findNearByDriver(NearByDriveRequestDto req);
}
