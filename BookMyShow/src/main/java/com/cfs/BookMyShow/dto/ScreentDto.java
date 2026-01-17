package com.cfs.BookMyShow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScreentDto {
    private Long id;
    private String name;
    private Integer totalSeats;
    private TheaterDto theater;
}
