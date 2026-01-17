package com.cfs.BookMyShow.service;


import com.cfs.BookMyShow.dto.TheaterDto;
import com.cfs.BookMyShow.exception.ResourceNotFoundException;
import com.cfs.BookMyShow.model.Theater;
import com.cfs.BookMyShow.repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterService {

    private TheaterRepository theaterRepository;

    public TheaterDto createTheater(TheaterDto theaterDto){

        Theater theater = mapToEntity(theaterDto);
        Theater saveTheater = theaterRepository.save(theater);
        return mapToDto(saveTheater);
    }

    public TheaterDto getTheaterById(Long id){
        Theater theater = theaterRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Theater Not Found: " + id));
        return mapToDto(theater);
    }

    public List<TheaterDto> getAllTheaters(){
        List<Theater> theaters = theaterRepository.findAll();
        return theaters.stream()
                .map(this::mapToDto).
                collect(Collectors.toList());
    }

    public List<TheaterDto> getTheaterByCity(String city){
        List<Theater> theaters = theaterRepository.findByCity(city);
        return theaters.stream()
                .map(this::mapToDto).
                collect(Collectors.toList());
    }

                                                                                 //update tehater homework tha chat gpt se kar diya tha

    public TheaterDto updateTheater(Long id, TheaterDto theaterDto) {

        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Theater Not Found: " + id));

        theater.setName(theaterDto.getName());
        theater.setCity(theaterDto.getCity());
        theater.setAddress(theaterDto.getAddress());
        theater.setTotalScreen(theaterDto.getTotalScreens());

        Theater updatedTheater = theaterRepository.save(theater);
        return mapToDto(updatedTheater);
    }

                                                                                     // delete theater y functionality add krnih h


    public void deleteTheater(Long id) {

        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Theater Not Found: " + id));

        theaterRepository.delete(theater);
    }




    public  Theater mapToEntity(TheaterDto theaterDto){
        Theater theater = new Theater();
        theater.setId(theaterDto.getId());
        theater.setName(theaterDto.getName());
        theater.setCity(theaterDto.getCity());
        theater.setAddress(theaterDto.getAddress());
        theater.setTotalScreen(theaterDto.getTotalScreens());
        return theater;
    }

    public TheaterDto mapToDto(Theater theater){
        TheaterDto theaterDto = new TheaterDto();
        theaterDto.setId(theater.getId());
        theaterDto.setCity(theater.getCity());
        theaterDto.setName(theater.getName());
        theaterDto.setTotalScreens(theater.getTotalScreen());
        theaterDto.setAddress(theater.getAddress());
        return theaterDto;
    }
}
