package com.example.UberProjectLocationService.service;

import com.example.UberProjectLocationService.dto.DriverLocationDto;
import com.example.UberProjectLocationService.dto.NearByDriveRequestDto;
import com.example.UberProjectLocationService.dto.SaveDriverLocationDto;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{
    private final String DRIVER_KEY = "driver";
    GeoOperations<String, String> geoOperations;

    public LocationServiceImpl(StringRedisTemplate stringRedisTemplate){
        this.geoOperations = stringRedisTemplate.opsForGeo();
    }

    @Override
    public Boolean saveDriverLocation(SaveDriverLocationDto req) {
        try{
            geoOperations.add(DRIVER_KEY,
                    new Point(req.getLongitude(),req.getLatitude()),
                    String.valueOf(req.getDriverId()));
            return Boolean.TRUE;
        }
        catch (Exception e){
            return Boolean.FALSE;
        }
    }

    @Override
    public List<DriverLocationDto> findNearByDriver(NearByDriveRequestDto req) {
        Distance distance = new Distance(5, Metrics.KILOMETERS);
        Circle circle = new Circle(new Point(req.getLongitude(), req.getLatitude()),distance);

        GeoResults<RedisGeoCommands.GeoLocation<String>> results = geoOperations.radius(DRIVER_KEY, circle);
        List<DriverLocationDto> drivers = new ArrayList<>();
        for(GeoResult<RedisGeoCommands.GeoLocation<String>> result : results) {
            Point point = geoOperations.position(DRIVER_KEY, result.getContent().getName()).get(0);
            DriverLocationDto driverLocation = DriverLocationDto.builder()
                    .driverId(result.getContent().getName())
                    .latitude(point.getY())
                    .longitude(point.getX())
                    .build();
            drivers.add(driverLocation);
        }
        return drivers;
    }
}
