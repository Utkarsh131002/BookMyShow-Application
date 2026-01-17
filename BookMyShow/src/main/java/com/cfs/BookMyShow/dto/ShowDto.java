package com.cfs.BookMyShow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDto {

    private  Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private MovieDto movie;
    private ScreentDto screen;
    private List<ShowSeatDto> availableSeats;

}
