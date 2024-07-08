package com.example.UberProjectLocationService.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NearByDriveRequestDto {
    private Double latitude;
    private Double longitude;
}
