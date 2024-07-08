package com.example.UberProjectLocationService.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveDriverLocationDto {
    private Long driverId;
    private Double latitude;
    private Double longitude;
}
