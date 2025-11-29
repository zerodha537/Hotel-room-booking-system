package com.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomSearchDTO {
    private String roomType;
    private Double minPrice;
    private Double maxPrice;
    private Integer capacity;
    private Long checkInDate;
    private Long checkOutDate;
    private Integer page;
    private Integer size;
}
