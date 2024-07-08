package com.example.UberProjectLocationService.controller;

import com.example.UberProjectLocationService.dto.DriverLocationDto;
import com.example.UberProjectLocationService.dto.NearByDriveRequestDto;
import com.example.UberProjectLocationService.dto.SaveDriverLocationDto;

import com.example.UberProjectLocationService.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService){
        this.locationService=locationService;
    }

    @PostMapping("/driver")
    public ResponseEntity<Boolean> addDriverLocation(@RequestBody SaveDriverLocationDto req){
       Boolean res = locationService.saveDriverLocation(req);
       if(res) return ResponseEntity.status(HttpStatus.CREATED).body(true);
       else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
    }

    @PostMapping("/nearest/drivers")
    public ResponseEntity<List<DriverLocationDto>> getNearByDriver(@RequestBody NearByDriveRequestDto req){
        List<DriverLocationDto> drivers = locationService.findNearByDriver(req);
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }
}

